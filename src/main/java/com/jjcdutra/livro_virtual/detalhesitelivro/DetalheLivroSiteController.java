package com.jjcdutra.livro_virtual.detalhesitelivro;

import com.jjcdutra.livro_virtual.exception.NotFoundException;
import com.jjcdutra.livro_virtual.novolivro.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detalhes-site")
public class DetalheLivroSiteController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/{id}")
    public DetalheLivroSiteResponse detalhe(@PathVariable Long id) {
        Livro livroBuscado = manager.find(Livro.class, id);
        if (livroBuscado == null) {
            throw new NotFoundException("Livro não encontrado na base de dados");
        }
        return new DetalheLivroSiteResponse(livroBuscado);
    }
}
