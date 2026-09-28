package br.com.doefacil.srv.ongs.service;

import br.com.doefacil.srv.ongs.controller.dto.req.LoginDtoReq;
import br.com.doefacil.srv.ongs.controller.dto.req.OngReqDto;
import br.com.doefacil.srv.ongs.controller.dto.resp.LoginRespDto;
import br.com.doefacil.srv.ongs.repository.OngRepository;
import br.com.doefacil.srv.ongs.repository.entity.EnderecoEntity;
import br.com.doefacil.srv.ongs.repository.entity.OngsEntity;
import br.com.doefacil.srv.ongs.repository.exception.UserNotFoundException;
import br.com.doefacil.srv.ongs.service.exception.PassWordNotMatch;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

@Service
public class OngsService implements IOngsService {

    private static final Logger logger = LoggerFactory.getLogger(OngsService.class);

    private final OngRepository ongRepository;
    private final EnderecoLookupService enderecoLookupService;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private static final String TERMOS_VERSAO = "1.0";
    private static final String PRIVACIDADE_VERSAO = "1.0";

    public OngsService(OngRepository ongRepository, EnderecoLookupService enderecoLookupService,
                       PasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.ongRepository = ongRepository;
        this.enderecoLookupService = enderecoLookupService;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public void saveNewRepo(OngReqDto req) {

        validaSenha(req);

        EnderecoCep enderecoConsultado = enderecoLookupService.consultar(req.cep());
        EnderecoEntity endereco = new EnderecoEntity(
            enderecoConsultado.logradouro(), req.numero(), enderecoConsultado.cep(), req.complemento());

        ongRepository.save(
                new OngsEntity(
                        req.nome(),
                        req.cnpj(),
                        req.email(),
                        req.telefone(),
                        req.area_autacao(),
                        passwordEncoder.encode(req.senha()),
                        endereco,
                        req.responsavel(),
                        req.cpf(),
                        TERMOS_VERSAO,
                        PRIVACIDADE_VERSAO,
                        Instant.now()
                )
        );
    }

    @Override
    public OngsEntity findById(Long id) {
        return ongRepository.findById(id).orElseThrow();
    }

    @Override
    public EnderecoCep consultarCep(String cep) {
        return enderecoLookupService.consultar(cep);
    }

    @Override
    public LoginRespDto login(LoginDtoReq login) {

        Optional<OngsEntity> ongResult = ongRepository.findByCnpj(login.cnpj());

        if (ongResult.isEmpty() || !senhaValida(ongResult.get(), login.senha())) {
            logger.warn("AUDIT event=LOGIN_FAILURE");
            throw new UserNotFoundException("Usuário não encontrado, verifique email e senha.");
        }

        OngsEntity ong = ongResult.get();
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plus(8, ChronoUnit.HOURS);
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("doefacil")
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .subject(ong.getId().toString())
                .claim("ongId", ong.getId().toString())
                .claim("scope", "ONG_ADMIN")
                .build();
        String token = jwtEncoder.encode(JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(), claims)).getTokenValue();
        logger.info("AUDIT event=LOGIN_SUCCESS ongId={}", ong.getId());

        return new LoginRespDto(ong.getId().toString(), Boolean.TRUE, token, 28800);
    }

    private boolean senhaValida(OngsEntity ong, String senhaInformada) {
        String senhaArmazenada = ong.getSenha();
        if (senhaArmazenada == null) {
            return false;
        }
        if (passwordEncoder.matches(senhaInformada, senhaArmazenada)) {
            return true;
        }
        if (!senhaArmazenada.startsWith("$2") && Objects.equals(senhaArmazenada, senhaInformada)) {
            ong.setSenha(passwordEncoder.encode(senhaInformada));
            ongRepository.save(ong);
            return true;
        }
        return false;
    }

    private void validaSenha(OngReqDto req) {
        if (!req.senha().equals(req.confirmacao_senha()))
            throw new PassWordNotMatch("As senhas precisam ser as mesmas.");
    }
}
