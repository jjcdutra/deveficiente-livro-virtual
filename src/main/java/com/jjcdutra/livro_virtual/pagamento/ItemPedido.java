package com.jjcdutra.livro_virtual.pagamento;

import com.jjcdutra.livro_virtual.novolivro.Livro;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class ItemPedido {

    @ManyToOne
    @NotNull
    private Livro livro;

    @Positive
    private int quantidade;

    @Positive
    private BigDecimal precoMomento;

    @Deprecated
    public ItemPedido() {
    }

    public ItemPedido(@NotNull Livro livro, @Positive int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
        this.precoMomento = livro.getPreco();
    }

    public BigDecimal total() {
        return precoMomento.multiply(new BigDecimal(quantidade));
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "livro=" + livro +
                ", quantidade=" + quantidade +
                ", precoMomento=" + precoMomento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(livro, that.livro);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(livro);
    }
}
