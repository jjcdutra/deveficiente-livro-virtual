package com.jjcdutra.livro_virtual.detalhesitelivro;

import com.jjcdutra.livro_virtual.novolivro.Livro;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public record DetalheLivroSiteResponse(
        String titulo,
        String resumo,
        String sumario,
        BigDecimal preco,
        Integer numeroPaginas,
        String isbn,
        String dataPublicacao,
        AutorSiteResponse autor
) {
    public DetalheLivroSiteResponse(Livro livroBuscado) {
        this(
                livroBuscado.getTitulo(),
                livroBuscado.getResumo(),
                livroBuscado.getSumario(),
                livroBuscado.getPreco(),
                livroBuscado.getNumeroPaginas(),
                livroBuscado.getIsbn(),
                livroBuscado.getDataPublicacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                new AutorSiteResponse(livroBuscado.getAutor())
        );
    }
}
