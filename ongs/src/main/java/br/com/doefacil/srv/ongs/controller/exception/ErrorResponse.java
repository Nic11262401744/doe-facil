package br.com.doefacil.srv.ongs.controller.exception;

public record ErrorResponse(
        Integer statusCode,
        String message,
        String timeStamp
) {
}