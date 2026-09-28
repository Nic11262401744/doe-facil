package br.com.doefacil.srv.ongs.controller;

import br.com.doefacil.srv.ongs.controller.dto.req.OngReqDto;
import br.com.doefacil.srv.ongs.controller.dto.resp.OngRespDto;
import br.com.doefacil.srv.ongs.repository.entity.OngsEntity;
import br.com.doefacil.srv.ongs.service.IOngsService;
import br.com.doefacil.srv.ongs.service.EnderecoCep;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/ongs")
@CrossOrigin(origins = "http://localhost:5173") 
public class OngsController {

    IOngsService service;

    public OngsController(IOngsService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createOng(@RequestBody @Valid OngReqDto req) {
        service.saveNewRepo(req);
    }

    @GetMapping("/cep/{cep}")
    ResponseEntity<EnderecoCep> lookupCep(@PathVariable String cep) {
        return ResponseEntity.ok(service.consultarCep(cep));
    }

    @GetMapping("/{id}")
    ResponseEntity<OngRespDto> findOng(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        if (!id.equals(Long.valueOf(jwt.getClaimAsString("ongId")))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso restrito à ONG autenticada");
        }
        OngsEntity ong = service.findById(id);

        return ResponseEntity.ok(
                new OngRespDto(
                        ong.getNome(),
                        ong.getcnpj(),
                        ong.getTelefone(),
                        ong.getTelefone(),
                        ong.getAreaAtuacao(),
                        ong.getEndereco().getRua()
                )
        );
    }
}