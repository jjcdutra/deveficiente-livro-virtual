package com.jjcdutra.livro_virtual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CadastroDuplicadoException.class)
    public ErrorResponse handleCadastroDuplicadoException(CadastroDuplicadoException exception) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), exception.getMessage(), null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldErrorResponse> errors = exception.getBindingResult().getFieldErrors().stream().map(fieldError -> new FieldErrorResponse(
                fieldError.getField(),
                fieldError.getDefaultMessage()
        )).toList();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), errors);
    }
}
