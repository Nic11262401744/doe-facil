package br.com.doefacil.doacao.controller;

import br.com.doefacil.doacao.controller.dto.req.DoacaoRequestDto;
import br.com.doefacil.doacao.controller.dto.resp.DoacaoDtoResp;
import br.com.doefacil.doacao.repository.entity.DoacaoEntity;
import br.com.doefacil.doacao.service.IDoeacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/doacao")
@CrossOrigin(origins = "http://localhost:5173") 
public class DoacaoController {

    IDoeacaoService service;

    public DoacaoController(IDoeacaoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void criarDoacao(@RequestBody @Valid DoacaoRequestDto requestDto, @AuthenticationPrincipal Jwt jwt) {
        service.createDoacao(requestDto, Long.valueOf(jwt.getClaimAsString("ongId")));
    }

    @GetMapping("/{id}")
    ResponseEntity<DoacaoDtoResp> findDoacao(@PathVariable("id") Long id, @AuthenticationPrincipal Jwt jwt) {
        DoacaoEntity doacao = service.findDoacao(id, Long.valueOf(jwt.getClaimAsString("ongId")));
        return ResponseEntity.ok(new DoacaoDtoResp(
                doacao.getId(),
                doacao.getTipo(),
                doacao.getDescricao(),
                doacao.getValor(),
                doacao.getData_doacao().toString(),
                doacao.getDoador(),
                doacao.getColaborador().getNome(),
                doacao.getOng().getId().toString()
        ));
    }

    @GetMapping("/byOng/{id}")
    ResponseEntity<ArrayList<DoacaoDtoResp>> findDoacoesByOng(@PathVariable("id") Long idOng, @AuthenticationPrincipal Jwt jwt) {
        Long ongIdToken = Long.valueOf(jwt.getClaimAsString("ongId"));
        if (!idOng.equals(ongIdToken)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso restrito à ONG autenticada");
        }
        ArrayList<DoacaoEntity> doacao = service.findDoacoes(ongIdToken);
        ArrayList<DoacaoDtoResp> res = new ArrayList();

        doacao.forEach(doacaoEntity -> {
            res.add(new DoacaoDtoResp(
                    doacaoEntity.getId(),
                    doacaoEntity.getTipo(),
                    doacaoEntity.getDescricao(),
                    doacaoEntity.getValor(),
                    doacaoEntity.getData_doacao().toString(),
                    doacaoEntity.getDoador(),
                    doacaoEntity.getColaborador().getNome(),
                    doacaoEntity.getOng().getId().toString()));
        });

        return ResponseEntity.ok(res);
    }
}
