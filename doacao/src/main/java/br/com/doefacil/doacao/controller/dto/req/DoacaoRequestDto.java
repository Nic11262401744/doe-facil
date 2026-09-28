package br.com.doefacil.doacao.controller.dto.req;

import jakarta.validation.constraints.NotBlank;

public record DoacaoRequestDto(
        @NotBlank
        String tipo,
        @NotBlank
        String descricao,
        Float valor,
        @NotBlank
        String data_doacao,
        String doador,
        @NotBlank
        String colaborador_nome,
        @NotBlank
        String ong_id
) {
}
