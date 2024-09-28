package com.jjcdutra.livro_virtual.detalhesitelivro;

import com.jjcdutra.livro_virtual.novoautor.Autor;

public record AutorSiteResponse(String nome, String descricao) {

    public AutorSiteResponse(Autor autor) {
        this(autor.getNome(), autor.getDescricao());
    }
}