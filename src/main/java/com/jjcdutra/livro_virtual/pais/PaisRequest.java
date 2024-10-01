package com.jjcdutra.livro_virtual.pais;

import com.jjcdutra.livro_virtual.validation.Unique;
import jakarta.validation.constraints.NotBlank;

public record PaisRequest(
        @NotBlank
        @Unique(entity = Pais.class, field = "nome")
        String nome
) {
}