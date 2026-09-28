package br.com.doefacil.srv.colaboradores.controller.req;

import jakarta.validation.constraints.NotBlank;

public record EnderecoReqDto(
        @NotBlank
        String rua,
        @NotBlank
        String numero,
        @NotBlank
        String cep,
        @NotBlank
        String complemento
) {}
