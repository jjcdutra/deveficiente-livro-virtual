package com.jjcdutra.livro_virtual.novolivro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jjcdutra.livro_virtual.novacategoria.Categoria;
import com.jjcdutra.livro_virtual.novoautor.Autor;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class NovoLivroRequest {

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "O resumo é obrigatório")
    @Size(max = 500, message = "O resumo deve ter no máximo 500 caracteres")
    private String resumo;

    private String sumario;

    @NotNull(message = "O preço é obrigatório")
    @Min(value = 20, message = "O preço mínimo é 20")
    private BigDecimal preco;

    @NotNull(message = "O número de páginas é obrigatório")
    @Min(value = 100, message = "O número mínimo de páginas é 100")
    private Integer numeroPaginas;

    @NotBlank(message = "O ISBN é obrigatório")
    private String isbn;

    @NotNull(message = "A data de publicação é obrigatória")
    @Future(message = "A data de publicação deve ser no futuro")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
    private LocalDate dataPublicacao;

    @NotNull(message = "A categoria não pode ser nula")
    private Long categoriaId;

    @NotNull(message = "O autor não pode ser nulo")
    private Long autorId;

    public NovoLivroRequest(String titulo,
                            String resumo,
                            String sumario,
                            BigDecimal preco,
                            Integer numeroPaginas,
                            String isbn,
                            @JsonProperty("dataPublicacao") LocalDate dataPublicacao,
                            Long categoriaId,
                            Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    @Override
    public String toString() {
        return "NovoLivroRequest{" +
                "titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", numeroPaginas=" + numeroPaginas +
                ", isbn='" + isbn + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", categoriaId=" + categoriaId +
                ", autorId=" + autorId +
                '}';
    }

    public Livro toModel(EntityManager manager) {
        Autor autor = manager.find(Autor.class, autorId);
        Categoria categoria = manager.find(Categoria.class, categoriaId);
        return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, categoria, autor);
    }
}
