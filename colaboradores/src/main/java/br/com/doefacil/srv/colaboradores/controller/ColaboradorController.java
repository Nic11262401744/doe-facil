package br.com.doefacil.srv.colaboradores.controller;

import br.com.doefacil.srv.colaboradores.controller.req.ColaboradorReqDto;
import br.com.doefacil.srv.colaboradores.controller.resp.ColaboradorRespDto;
import br.com.doefacil.srv.colaboradores.repository.entity.ColaboradorEntity;
import br.com.doefacil.srv.colaboradores.service.iservice.IColaboradorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping("/Colaboradores")
@CrossOrigin(origins = "http://localhost:5173") 
public class ColaboradorController {

    private IColaboradorService iColaboradorService;

    public ColaboradorController(IColaboradorService iColaboradorService) {
        this.iColaboradorService = iColaboradorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createNewColaborador(@RequestBody @Valid ColaboradorReqDto req, @AuthenticationPrincipal Jwt jwt) {
        iColaboradorService.creatColaborador(req, Long.valueOf(jwt.getClaimAsString("ongId")));
    }

    @GetMapping("/{id}")
    ResponseEntity<ColaboradorRespDto> findColaborador(@PathVariable("id") Long id, @AuthenticationPrincipal Jwt jwt ){
        ColaboradorEntity entity =  iColaboradorService.findById(id);
        validarAcesso(entity.getOng().getId(), jwt);
        return new ResponseEntity<ColaboradorRespDto>(
                new ColaboradorRespDto(
                        entity.getNome(),
                        entity.getCpf(),
                        entity.getEmail(),
                        entity.getTelefone(),
                        entity.getCargo(),
                        entity.getOng().getId()
                ), HttpStatus.OK);
    }

    @GetMapping("/byOng/{id}")
    ResponseEntity<ArrayList<ColaboradorRespDto>> findColaboradorByOng(@PathVariable("id") Long id, @AuthenticationPrincipal Jwt jwt ){
        validarAcesso(id, jwt);
        ArrayList<ColaboradorEntity> entity =  iColaboradorService.findByOngId(id);
        ArrayList<ColaboradorRespDto> resp = new ArrayList();

        entity.forEach(colaboradorEntity -> {
            resp.add(new ColaboradorRespDto(
                    colaboradorEntity.getNome(),
                    colaboradorEntity.getCpf(),
                    colaboradorEntity.getEmail(),
                    colaboradorEntity.getTelefone(),
                    colaboradorEntity.getCargo(),
                    colaboradorEntity.getOng().getId()
            ));
        });

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    private void validarAcesso(Long ongId, Jwt jwt) {
        if (!ongId.equals(Long.valueOf(jwt.getClaimAsString("ongId")))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso restrito à ONG autenticada");
        }
    }
}
