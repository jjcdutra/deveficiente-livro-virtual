package com.jjcdutra.livro_virtual.novolivro;

public class LivroResponse {

    private Long id;
    private String nome;

    public LivroResponse(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
