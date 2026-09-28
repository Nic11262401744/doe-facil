package br.com.doefacil.srv.ongs.controller.dto.req;

import jakarta.validation.constraints.NotBlank;

public record LoginDtoReq(
        @NotBlank
        String cnpj,
        @NotBlank
        String senha
) {
}
