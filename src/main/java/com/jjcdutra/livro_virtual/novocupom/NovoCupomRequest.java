package com.jjcdutra.livro_virtual.novocupom;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jjcdutra.livro_virtual.validation.Unique;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NovoCupomRequest(

        @NotBlank
        @Unique(domainClass = Cupom.class, fieldName = "codigo")
        String codigo,

        @NotNull
        @Positive
        BigDecimal percentual,

        @Future
        @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
        LocalDate validade
) {

    @Override
    public String toString() {
        return "NovoCupomRequest{" +
                "codigo='" + codigo + '\'' +
                ", percentual=" + percentual +
                ", validadeMomento=" + validade +
                '}';
    }

    public Cupom toModel() {
        return new Cupom(
                this.codigo,
                this.percentual,
                this.validade
        );
    }
}
