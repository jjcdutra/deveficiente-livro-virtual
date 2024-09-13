package com.jjcdutra.livro_virtual.novacategoria;

import com.jjcdutra.livro_virtual.exception.CadastroDuplicadoException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Transactional
    public Categoria criar(NovaCategoriaRequest request) {
        if (repository.existsByNome(request.getNome())) {
            throw new CadastroDuplicadoException("Categoria já existe.");
        }
        Categoria categoria = request.toModel();
        return repository.save(categoria);
    }
}
