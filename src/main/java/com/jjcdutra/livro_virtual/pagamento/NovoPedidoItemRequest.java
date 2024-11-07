package com.jjcdutra.livro_virtual.pagamento;

import com.jjcdutra.livro_virtual.novolivro.Livro;
import com.jjcdutra.livro_virtual.validation.ExistsId;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class NovoPedidoItemRequest {

    @NotNull(message = "O id do livro é obrigatório.")
    @ExistsId(domainClass = Livro.class, fieldName = "id")
    private Long idLivro;

    @Positive
    private int quantidade;

    public NovoPedidoItemRequest(Long idLivro, int quantidade) {
        super();
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    @Override
    public String toString() {
        return "NovoItemPedidoRequest{" +
                "idLivro=" + idLivro +
                ", quantidade=" + quantidade +
                '}';
    }

    public ItemPedido toModel(EntityManager manager) {
        @NotNull Livro livro = manager.find(Livro.class, idLivro);
        return new ItemPedido(livro, quantidade);
    }
}
