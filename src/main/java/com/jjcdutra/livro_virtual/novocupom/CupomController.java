package com.jjcdutra.livro_virtual.novocupom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cupons")
public class CupomController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public String cria(@Valid @RequestBody NovoCupomRequest novoCupomRequest) {
        Cupom novoCupom = novoCupomRequest.toModel();
        manager.persist(novoCupom);
        return novoCupom.toString();
    }
}
