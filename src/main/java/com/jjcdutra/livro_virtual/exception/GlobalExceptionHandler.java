package com.jjcdutra.livro_virtual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CadastroDuplicadoException.class)
    public ErrorResponse handleCadastroDuplicadoException(CadastroDuplicadoException exception) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), exception.getMessage());
    }
}
