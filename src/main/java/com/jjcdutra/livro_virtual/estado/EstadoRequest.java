package com.jjcdutra.livro_virtual.estado;

import com.jjcdutra.livro_virtual.pais.Pais;
import com.jjcdutra.livro_virtual.validation.Unique;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EstadoRequest(
        @NotBlank
        @Unique(domainClass = Pais.class, fieldName = "nome")
        String nome,

        @NotNull
        Long paisId
) {

    @Override
    public String toString() {
        return "EstadoRequest{" +
                "nome='" + nome + '\'' +
                ", paisId=" + paisId +
                '}';
    }

    public Estado toModel(EntityManager manager) {
        Pais pais = manager.find(Pais.class, paisId);
        return new Estado(
                this.nome,
                pais
        );
    }
}