package com.jjcdutra.livro_virtual.pagamento;

import com.jjcdutra.livro_virtual.estado.Estado;
import com.jjcdutra.livro_virtual.novocupom.Cupom;
import com.jjcdutra.livro_virtual.novocupom.CupomAplicado;
import com.jjcdutra.livro_virtual.pais.Pais;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Formato de e-mail inválido")
    @NotBlank(message = "O campo e-mail é obrigatório")
    private String email;

    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @NotBlank(message = "O campo sobrenome é obrigatório")
    private String sobrenome;

    @Pattern(regexp = "\\d{11}|\\d{14}", message = "Documento deve ser CPF ou CNPJ válido")
    @NotBlank(message = "O campo documento é obrigatório")
    private String documento;

    @NotBlank(message = "O campo endereço é obrigatório")
    private String endereco;

    @NotBlank(message = "O campo complemento é obrigatório")
    private String complemento;

    @ManyToOne
    private Pais pais;

    @ManyToOne
    private Estado estado;

    @NotBlank(message = "O campo telefone é obrigatório")
    private String telefone;

    @NotBlank(message = "O campo CEP é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato XXXXX-XXX")
    private String cep;

    @OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
    private Pedido pedido;

    @Embedded
    private CupomAplicado cupomAplicado;

    @Deprecated
    public Compra() {
    }

    public Compra(String email,
                  String nome,
                  String sobrenome,
                  String documento,
                  String endereco,
                  String complemento,
                  Pais pais,
                  String telefone,
                  String cep, Function<Compra, Pedido> funcaoCriacaoPedido) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = funcaoCriacaoPedido.apply(this);
    }

    public void setEstado(Estado estado) {
        Assert.notNull(pais, "Não rola associar um estado enquanto o pais for nulo");
        Assert.isTrue(estado.pertenceAPais(pais), "Este estado não é do pais associado a compra");
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", pais=" + pais +
                ", estado=" + estado +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", pedido=" + pedido +
                ", cupomAplicado=" + cupomAplicado +
                '}';
    }

    public void aplicaCupom(Cupom cupom) {
        Assert.isTrue(cupom.isValid(), "Cupom aplicado não está valido");
        Assert.isNull(this.cupomAplicado, "Não pode ser trocado um cupom de uma compra");
        this.cupomAplicado = new CupomAplicado(cupom);
    }

    private BigDecimal aplicaDescontoCupom(ItemPedido itemPedido) {
        BigDecimal valorDesconto = itemPedido.getPrecoMomento()
                .multiply(cupomAplicado.getPercentualDescontoMomento()
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));

        return itemPedido.getPrecoMomento().subtract(valorDesconto);
    }

    public CompraResponse toResponse() {
        return pedido.getItens().stream().map(item -> new CompraResponse(
                        item.getLivro().getTitulo(),
                        item.getLivro().getIsbn(),
                        item.getLivro().getDataPublicacao(),
                        item.getLivro().getCategoria().getNome(),
                        item.getLivro().getAutor().getNome(),
                        item.getQuantidade(),
                        pedido.getTotalPedido(),
                        !ObjectUtils.isEmpty(cupomAplicado),
                        !ObjectUtils.isEmpty(cupomAplicado) ? aplicaDescontoCupom(item) : null
                )
        ).findFirst().orElseThrow();
    }
}
