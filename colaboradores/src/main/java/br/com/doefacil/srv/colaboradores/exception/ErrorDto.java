package br.com.doefacil.srv.colaboradores.exception;

public record ErrorDto(
        String message,
        String stackTrace,
        String httStatus
) {
}
