package com.jjcdutra.livro_virtual.pagamento;

import com.jjcdutra.livro_virtual.estado.Estado;
import com.jjcdutra.livro_virtual.pais.Pais;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.util.Assert;

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
                '}';
    }
}