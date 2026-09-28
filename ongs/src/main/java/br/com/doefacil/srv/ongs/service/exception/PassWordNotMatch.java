package br.com.doefacil.srv.ongs.service.exception;

public class PassWordNotMatch extends RuntimeException {
    public PassWordNotMatch(String message) {
        super(message);
    }
}
