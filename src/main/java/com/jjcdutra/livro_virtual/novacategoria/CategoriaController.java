package com.jjcdutra.livro_virtual.novacategoria;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public String criar(@RequestBody @Valid NovaCategoriaRequest request) {
        Categoria categoria = service.criar(request);
        return categoria.toString();
    }
}
