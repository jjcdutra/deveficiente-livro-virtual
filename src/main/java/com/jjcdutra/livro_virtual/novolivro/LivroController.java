package com.jjcdutra.livro_virtual.novolivro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public String criar(@Valid @RequestBody NovoLivroRequest request) {
        Livro novoLivro = request.toModel(manager);
        manager.persist(novoLivro);
        return novoLivro.toString();
    }
}
