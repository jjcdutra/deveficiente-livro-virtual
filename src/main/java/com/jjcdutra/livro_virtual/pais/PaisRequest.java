package com.jjcdutra.livro_virtual.pais;

import com.jjcdutra.livro_virtual.validation.Unique;
import jakarta.validation.constraints.NotBlank;

public record PaisRequest(
        @NotBlank
        @Unique(domainClass = Pais.class, fieldName = "nome")
        String nome
) {
}