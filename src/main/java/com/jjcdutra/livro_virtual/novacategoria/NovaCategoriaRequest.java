package com.jjcdutra.livro_virtual.novacategoria;

import com.jjcdutra.livro_virtual.validation.Unique;
import jakarta.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Unique(domainClass = Categoria.class, fieldName = "nome", message = "Nome da categoria já existe.")
    private String nome;

    @Deprecated
    public NovaCategoriaRequest() {
    }

    public NovaCategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O nome da categoria é obrigatório.") String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "NovaCategoriaRequest{" +
                "nome='" + nome + '\'' +
                '}';
    }

    public Categoria toModel() {
        return new Categoria(this.nome);
    }
}
