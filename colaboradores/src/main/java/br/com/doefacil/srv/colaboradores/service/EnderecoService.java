package br.com.doefacil.srv.colaboradores.service;

import br.com.doefacil.srv.colaboradores.repository.EnderecoRepository;
import br.com.doefacil.srv.colaboradores.repository.entity.EnderecoEntity;
import br.com.doefacil.srv.colaboradores.service.iservice.IEnderecoService;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;


@Service
public class EnderecoService implements IEnderecoService {

    private final EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createEndereco(EnderecoEntity endereco) {
        repository.save(endereco);
    }

    @Override
    public EnderecoEntity findByID(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public EnderecoEntity findByParam(String cep, String numero, String rua) {
        if (!cep.isBlank())
           return repository.findByCep(cep).orElseThrow();

        if (rua.isBlank() || numero.isBlank()) {
            throw new InvalidParameterException("Passe os valores de maneira correta.");
        } else {
            return repository.findByRuaAndNumero(rua, numero).orElseThrow();
        }
    }
}
