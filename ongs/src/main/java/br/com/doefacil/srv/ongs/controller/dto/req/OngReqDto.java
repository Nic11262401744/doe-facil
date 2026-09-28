package br.com.doefacil.srv.ongs.controller.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

public record OngReqDto(
        @NotBlank
        String nome,
        @NotBlank
        String area_autacao,
        @NotBlank
        String cnpj,
        @NotBlank
        String cep,
        @NotBlank
        String numero,
        String complemento,
        @NotBlank
        String senha,
        @NotBlank
        String confirmacao_senha,
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String responsavel,
        @NotBlank
        String cpf,
        @NotNull @AssertTrue Boolean aceiteTermos,
        @NotNull @AssertTrue Boolean aceitePrivacidade
){
}