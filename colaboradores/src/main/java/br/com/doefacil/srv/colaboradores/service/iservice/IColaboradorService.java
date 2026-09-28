package br.com.doefacil.srv.colaboradores.service.iservice;

import br.com.doefacil.srv.colaboradores.controller.req.ColaboradorReqDto;
import br.com.doefacil.srv.colaboradores.repository.entity.ColaboradorEntity;

import java.util.ArrayList;

public interface IColaboradorService {
    void creatColaborador(ColaboradorReqDto colaborador, Long ongId);
    ColaboradorEntity findById(Long id);

    ArrayList<ColaboradorEntity> findByOngId(Long id);
}
