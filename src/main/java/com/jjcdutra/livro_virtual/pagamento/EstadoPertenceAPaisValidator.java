package com.jjcdutra.livro_virtual.pagamento;

import com.jjcdutra.livro_virtual.estado.Estado;
import com.jjcdutra.livro_virtual.pais.Pais;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EstadoPertenceAPaisValidator implements Validator {

    private EntityManager manager;

    public EstadoPertenceAPaisValidator(EntityManager manager) {
        super();
        this.manager = manager;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovaCompraRequest request = (NovaCompraRequest) target;

        if (request.temEstado()) {
            Pais pais = manager.find(Pais.class, request.paisId());
            Estado estado = manager.find(Estado.class, request.estadoId());
            if (!estado.pertenceAPais(pais)){
                errors.rejectValue("estadoId", null, "O estado não pertence ao pais");
            }
        }
    }
}
