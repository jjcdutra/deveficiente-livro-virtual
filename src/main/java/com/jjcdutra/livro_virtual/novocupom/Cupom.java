package com.jjcdutra.livro_virtual.novocupom;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String codigo;

    @NotNull
    @Positive
    private BigDecimal percentual;

    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate validade;

    @Deprecated
    public Cupom() {
    }

    public Cupom(String codigo, BigDecimal percentual, LocalDate validade) {
        this.codigo = codigo;
        this.percentual = percentual;
        this.validade = validade;
    }

    public boolean isValid() {
        return LocalDate.now().isBefore(this.validade);
    }

    public @NotBlank String getCodigo() {
        return codigo;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public @NotNull @Positive BigDecimal getPercentual() {
        return percentual;
    }

    @Override
    public String toString() {
        return "Cupom{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", percentual=" + percentual +
                ", validadeMomento=" + validade +
                '}';
    }
}
