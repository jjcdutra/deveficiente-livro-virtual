package com.jjcdutra.livro_virtual.exception;

public record FieldErrorResponse(
        String field,
        String message
) {
}
