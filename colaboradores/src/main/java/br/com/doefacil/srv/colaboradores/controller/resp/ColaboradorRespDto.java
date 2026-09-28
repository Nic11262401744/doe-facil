package br.com.doefacil.srv.colaboradores.controller.resp;

public record ColaboradorRespDto(
        String nome,
        String cpf,
        String email,
        String telefone,
        String cargo,
        Long idOng
){}