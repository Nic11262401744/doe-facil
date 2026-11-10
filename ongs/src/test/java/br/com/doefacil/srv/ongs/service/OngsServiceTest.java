package br.com.doefacil.srv.ongs.service;

import br.com.doefacil.srv.ongs.controller.dto.req.OngReqDto;
import br.com.doefacil.srv.ongs.repository.OngRepository;
import br.com.doefacil.srv.ongs.repository.entity.OngsEntity;
import br.com.doefacil.srv.ongs.service.exception.PassWordNotMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OngsServiceTest {

    private OngRepository ongRepository;
    private EnderecoLookupService enderecoLookupService;
    private BCryptPasswordEncoder passwordEncoder;
    private OngsService service;

    @BeforeEach
    void setUp() {
        ongRepository = mock(OngRepository.class);
        enderecoLookupService = mock(EnderecoLookupService.class);
        passwordEncoder = new BCryptPasswordEncoder();
        service = new OngsService(
                ongRepository,
                enderecoLookupService,
                passwordEncoder,
                mock(JwtEncoder.class)
        );
    }

    @Test
    void saveNewRepoStoresPasswordEncodedAndAddressFromLookup() {
        when(enderecoLookupService.consultar("01001000"))
                .thenReturn(new EnderecoCep("01001-000", "Praça da Sé", "São Paulo", "SP"));

        service.saveNewRepo(ongRequest("senha-segura", "senha-segura"));

        var savedOng = org.mockito.ArgumentCaptor.forClass(OngsEntity.class);
        verify(ongRepository).save(savedOng.capture());

        OngsEntity ong = savedOng.getValue();
        assertNotEquals("senha-segura", ong.getSenha());
        assertTrue(passwordEncoder.matches("senha-segura", ong.getSenha()));
        assertEquals("Praça da Sé", ong.getEndereco().getRua());
        assertEquals("01001-000", ong.getEndereco().getCep());
        assertEquals("123", ong.getEndereco().getNumero());
    }

    @Test
    void saveNewRepoRejectsDifferentPasswordConfirmation() {
        assertThrows(
                PassWordNotMatch.class,
                () -> service.saveNewRepo(ongRequest("senha-segura", "outra-senha"))
        );

        verify(ongRepository, never()).save(any(OngsEntity.class));
        verify(enderecoLookupService, never()).consultar(any());
    }

    private OngReqDto ongRequest(String senha, String confirmacaoSenha) {
        return new OngReqDto(
                "ONG Exemplo",
                "Assistência social",
                "00.000.000/0001-00",
                "01001000",
                "123",
                "Sala 1",
                senha,
                confirmacaoSenha,
                "contato@ong.com",
                "11999999999",
                "Responsável",
                "000.000.000-00",
                true,
                true
        );
    }
}
