package com.jjcdutra.livro_virtual.estado;

import com.jjcdutra.livro_virtual.pais.Pais;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @ManyToOne
    private Pais pais;

    @Deprecated
    public Estado() {
    }

    public Estado(@NotBlank String nome, @NotNull Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public @NotNull Pais getPais() {
        return pais;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", pais=" + pais +
                '}';
    }

    public boolean pertenceAPais(Pais pais) {
        return this.pais.equals(pais);
    }
}
