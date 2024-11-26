package com.jjcdutra.livro_virtual.pagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CompraResponse {
    private String titulo;
    private String isbn;
    private LocalDate dataPublicacao;
    private String categoriaNome;
    private String autorNome;
    private int quantidade;
    private BigDecimal valorTotalCompra;
    private Boolean cupom;
    private BigDecimal precoDescontoCupom;

    @Deprecated
    public CompraResponse() {
    }

    public CompraResponse(String titulo,
                          String isbn,
                          LocalDate dataPublicacao,
                          String categoriaNome,
                          String autorNome,
                          int quantidade,
                          BigDecimal valorTotalCompra,
                          Boolean cupom,
                          BigDecimal precoDescontoCupom) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoriaNome = categoriaNome;
        this.autorNome = autorNome;
        this.quantidade = quantidade;
        this.valorTotalCompra = valorTotalCompra;
        this.cupom = cupom;
        this.precoDescontoCupom = precoDescontoCupom;
    }

    @Override
    public String toString() {
        return "CompraResponse{" +
                "titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", categoriaNome='" + categoriaNome + '\'' +
                ", autorNome='" + autorNome + '\'' +
                ", quantidade=" + quantidade +
                ", valorTotalCompra=" + valorTotalCompra +
                ", cupom=" + cupom +
                ", precoDescontoCupom=" + precoDescontoCupom +
                '}';
    }
}
