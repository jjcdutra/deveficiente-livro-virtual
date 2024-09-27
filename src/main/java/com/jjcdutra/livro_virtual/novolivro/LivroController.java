package com.jjcdutra.livro_virtual.novolivro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository repository;

    @PersistenceContext
    private EntityManager manager;

    public LivroController(LivroRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public String criar(@Valid @RequestBody NovoLivroRequest request) {
        Livro novoLivro = request.toModel(manager);
        manager.persist(novoLivro);
        return novoLivro.toString();
    }

    @GetMapping
    public List<LivroResponse> listar() {
        List<Livro> livros = repository.findAll();
        return livros.stream()
                .map(livro -> new LivroResponse(livro.getId(), livro.getTitulo()))
                .toList();
    }
}
