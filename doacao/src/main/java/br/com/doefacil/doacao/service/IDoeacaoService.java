package br.com.doefacil.doacao.service;

import br.com.doefacil.doacao.controller.dto.req.DoacaoRequestDto;
import br.com.doefacil.doacao.repository.entity.DoacaoEntity;

import java.util.ArrayList;

public interface IDoeacaoService {

    void createDoacao(DoacaoRequestDto req, Long ongId);

    DoacaoEntity findDoacao(Long id, Long ongId);

    ArrayList<DoacaoEntity> findDoacoes(Long idOng);
}
