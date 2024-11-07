package com.jjcdutra.livro_virtual.pagamento;

import com.jjcdutra.livro_virtual.estado.Estado;
import com.jjcdutra.livro_virtual.pais.Pais;
import com.jjcdutra.livro_virtual.validation.ExistsId;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Optional;
import java.util.function.Function;

public record NovaCompraRequest(

        @Email(message = "Formato de e-mail inválido")
        @NotBlank(message = "O campo e-mail é obrigatório")
        String email,

        @NotBlank(message = "O campo nome é obrigatório")
        String nome,

        @NotBlank(message = "O campo sobrenome é obrigatório")
        String sobrenome,

        @Pattern(regexp = "\\d{11}|\\d{14}", message = "Documento deve ser CPF ou CNPJ válido")
        @NotBlank(message = "O campo documento é obrigatório")
        String documento,

        @NotBlank(message = "O campo endereço é obrigatório")
        String endereco,

        @NotBlank(message = "O campo complemento é obrigatório")
        String complemento,

        @NotBlank(message = "O campo cidade é obrigatório")
        String cidade,

        @ExistsId(domainClass = Pais.class, fieldName = "id")
        @NotNull(message = "O campo país é obrigatório")
        Long paisId,

        @ExistsId(domainClass = Estado.class, fieldName = "id")
        Long estadoId, // só obrigatório se o país tiver estado

        @NotBlank(message = "O campo telefone é obrigatório")
        String telefone,

        @NotBlank(message = "O campo CEP é obrigatório")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato XXXXX-XXX")
        String cep,

        @NotNull
        NovoPedidoRequest pedido
) {
    public boolean temEstado() {
        return Optional.ofNullable(estadoId).isPresent();
    }

    public Compra toModel(EntityManager manager) {
        Pais pais = manager.find(Pais.class, paisId);

        Function<Compra, Pedido> funcaoCriacaoPedido = pedido.toModel(manager);

        Compra compra = new Compra(email, nome, sobrenome, documento, endereco, complemento, pais, telefone, cep, funcaoCriacaoPedido);
        if (estadoId != null) {
            compra.setEstado(manager.find(Estado.class, estadoId));
        }


        return compra;
    }
}