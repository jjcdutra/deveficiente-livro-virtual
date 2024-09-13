package com.jjcdutra.livro_virtual.exception;

public class CadastroDuplicadoException extends RuntimeException {
    public CadastroDuplicadoException(String message) {
        super(message);
    }
}
