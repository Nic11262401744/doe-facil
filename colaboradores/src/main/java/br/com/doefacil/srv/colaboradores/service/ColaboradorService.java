package br.com.doefacil.srv.colaboradores.service;

import br.com.doefacil.srv.colaboradores.controller.req.ColaboradorReqDto;
import br.com.doefacil.srv.colaboradores.repository.ColaboradorRepository;
import br.com.doefacil.srv.colaboradores.repository.entity.ColaboradorEntity;
import br.com.doefacil.srv.colaboradores.service.iservice.IColaboradorService;
import br.com.doefacil.srv.colaboradores.service.iservice.IEnderecoService;
import br.com.doefacil.srv.colaboradores.service.iservice.IOngService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ColaboradorService implements IColaboradorService {

    private final IEnderecoService enderecoService;
    private final IOngService ongService;
    private final ColaboradorRepository repository;

    public ColaboradorService(IEnderecoService enderecoService, IOngService ongService, ColaboradorRepository repository) {
        this.enderecoService = enderecoService;
        this.ongService = ongService;
        this.repository = repository;
    }

    @Override
    public void creatColaborador(ColaboradorReqDto colaborador, Long ongId) {

        repository.save(
                new ColaboradorEntity(
                    colaborador.nome(),
                    colaborador.cpf(),
                    colaborador.email(),
                    colaborador.telefone(),
                    colaborador.cargo(),
                    ongService.findById(ongId)
                )
        );
    }

    @Override
    public ColaboradorEntity findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public ArrayList<ColaboradorEntity> findByOngId(Long id) {
        return repository.findByIdOng(id);
    }
}
