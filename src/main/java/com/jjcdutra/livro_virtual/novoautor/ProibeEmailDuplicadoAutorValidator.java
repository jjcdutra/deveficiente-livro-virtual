package com.jjcdutra.livro_virtual.novoautor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeEmailDuplicadoAutorValidator implements Validator {

    @Autowired
    private AutorRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoAutorRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovoAutorRequest request = (NovoAutorRequest) target;
        Optional<Autor> possivelAutor = repository.findByEmail(request.getEmail());

        if (possivelAutor.isPresent()) {
            errors.rejectValue("email", null, "E-mail já está cadastrado para esse autor");
        }
    }
}
