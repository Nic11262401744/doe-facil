package br.com.doefacil.srv.colaboradores.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<ErrorDto> restNoSuchElementException(NoSuchElementException ex) {

        return new ResponseEntity(
                new ErrorDto(
                        ex.getMessage(),
                        ex.getStackTrace().toString(),
                        HttpStatus.NOT_FOUND.toString()
                ),
                HttpStatus.NOT_FOUND
        );
    }
}
