package br.com.doefacil.doacao.service;

import br.com.doefacil.doacao.controller.dto.req.DoacaoRequestDto;
import br.com.doefacil.doacao.repository.ColaboradorRepository;
import br.com.doefacil.doacao.repository.DoacaoRepository;
import br.com.doefacil.doacao.repository.OngRepository;
import br.com.doefacil.doacao.repository.entity.DoacaoEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class DoeacaoService implements IDoeacaoService {

    DoacaoRepository doacaoRepository;
    OngRepository ongRepository;
    ColaboradorRepository colaboradorRepository;

    public DoeacaoService(DoacaoRepository doacaoRepository, OngRepository ongRepository, ColaboradorRepository colaboradorRepository) {
        this.doacaoRepository = doacaoRepository;
        this.ongRepository = ongRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    public void createDoacao(DoacaoRequestDto req, Long ongId) {

        LocalDate date = LocalDate.parse(req.data_doacao(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        doacaoRepository.save(
                new DoacaoEntity(
                        req.tipo(),
                        req.descricao(),
                        req.valor(),
                        date,
                        req.doador(),
                        colaboradorRepository.findByNomeAndOng_Id(req.colaborador_nome(), ongId).orElseThrow(),
                        ongRepository.findById(ongId).orElseThrow()
                )
        );
    }

    @Override
    public DoacaoEntity findDoacao(Long id, Long ongId) {
        return doacaoRepository.findByIdAndOng_Id(id, ongId).orElseThrow();
    }

    @Override
    public ArrayList<DoacaoEntity> findDoacoes(Long idOng) {
        return doacaoRepository.seachDoacoesByOngId(idOng);
    }
}
