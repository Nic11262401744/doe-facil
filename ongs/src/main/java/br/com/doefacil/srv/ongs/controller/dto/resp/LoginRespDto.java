package br.com.doefacil.srv.ongs.controller.dto.resp;

public record LoginRespDto(String idOng, Boolean isLogged, String token, long expiresIn){
}
