package br.com.doefacil.srv.colaboradores.service;

import br.com.doefacil.srv.colaboradores.repository.OngRepository;
import br.com.doefacil.srv.colaboradores.repository.entity.OngEntity;
import br.com.doefacil.srv.colaboradores.service.iservice.IOngService;
import org.springframework.stereotype.Service;

@Service
public class OngService implements IOngService {

    private final OngRepository repository;

    public OngService(OngRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createOng(OngEntity ong) {

    }

    @Override
    public OngEntity findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
