package com.jjcdutra.livro_virtual.novoautor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProibeEmailDuplicadoAutorValidator proibeEmailDuplicadoAutorValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(proibeEmailDuplicadoAutorValidator);
    }

    @PostMapping
    @Transactional
    public String criar(@RequestBody @Valid NovoAutorRequest request) {
        Autor autor = request.toModel();
        manager.persist(autor);
        return autor.toString();
    }
}
