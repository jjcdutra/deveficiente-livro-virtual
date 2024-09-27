package com.jjcdutra.livro_virtual.novolivro;

import com.jjcdutra.livro_virtual.novacategoria.Categoria;
import com.jjcdutra.livro_virtual.novoautor.Autor;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Column(unique = true, nullable = false)
    private String titulo;

    @NotBlank(message = "O resumo é obrigatório")
    @Size(max = 500, message = "O resumo deve ter no máximo 500 caracteres")
    private String resumo;

    @Column(columnDefinition = "TEXT")
    private String sumario;

    @NotNull(message = "O preço é obrigatório")
    @Min(value = 20, message = "O preço mínimo é 20")
    private Double preco;

    @NotNull(message = "O número de páginas é obrigatório")
    @Min(value = 100, message = "O número mínimo de páginas é 100")
    private Integer numeroPaginas;

    @NotBlank(message = "O ISBN é obrigatório")
    @Column(unique = true, nullable = false)
    private String isbn;

    @NotNull(message = "A data de publicação é obrigatória")
    @Future(message = "A data de publicação deve ser no futuro")
    private LocalDate dataPublicacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @Deprecated
    public  Livro() {}

    public Livro(String titulo, String resumo, String sumario, Double preco, Integer numeroPaginas, String isbn, LocalDate dataPublicacao, Categoria categoria, Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", numeroPaginas=" + numeroPaginas +
                ", isbn='" + isbn + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", categoria=" + categoria +
                ", autor=" + autor +
                '}';
    }
}