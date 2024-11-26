package com.jjcdutra.livro_virtual.novocupom;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
public class CupomAplicado {

    @ManyToOne
    private Cupom cupom;

    @NotNull
    private BigDecimal percentualDescontoMomento;

    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @NotNull
    private LocalDate validadeMomento;

    @Deprecated
    public CupomAplicado() {
    }

    public CupomAplicado(Cupom cupom) {
        this.cupom = cupom;
        this.percentualDescontoMomento = cupom.getPercentual();
        this.validadeMomento = cupom.getValidade();
    }

    public @NotNull BigDecimal getPercentualDescontoMomento() {
        return percentualDescontoMomento;
    }

    @Override
    public String toString() {
        return "CupomAplicado{" +
                "cupom=" + cupom +
                ", percentualDescontoMomento=" + percentualDescontoMomento +
                ", validadeMomento=" + validadeMomento +
                '}';
    }
}