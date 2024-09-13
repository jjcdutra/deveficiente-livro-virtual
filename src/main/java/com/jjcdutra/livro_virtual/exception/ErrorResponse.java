package com.jjcdutra.livro_virtual.exception;

import java.util.List;

public record ErrorResponse(
        int status,
        String message,
        List<FieldErrorResponse> errors
) {
}
