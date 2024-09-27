package com.jjcdutra.livro_virtual.novacategoria;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Column(nullable = false, unique = true)
    private String nome;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public @NotBlank(message = "O nome da categoria é obrigatório.") String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
