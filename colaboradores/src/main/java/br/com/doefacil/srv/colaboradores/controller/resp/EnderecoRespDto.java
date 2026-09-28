package br.com.doefacil.srv.colaboradores.controller.resp;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRespDto(@NotBlank
                              String rua,
                              @NotBlank
                              String numero,
                              @NotBlank
                              String cep,
                              @NotBlank
                              String complemento
) { }