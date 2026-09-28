package br.com.doefacil.srv.ongs.controller.dto.resp;

public record OngRespDto(
        String nome,
        String cnpj,
        String telefone,
        String email,
        String area_autacao,
        String nome_rua
) {
}
