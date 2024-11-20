package com.jjcdutra.livro_virtual.pagamento;

import com.jjcdutra.livro_virtual.novocupom.CupomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
public class PagamentoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CupomRepository cupomRepository;

    @Autowired
    private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(estadoPertenceAPaisValidator);
    }

    @PostMapping
    @Transactional
    public String criar(@RequestBody @Valid NovaCompraRequest request) {
        Compra compra = request.toModel(manager, cupomRepository);
        manager.persist(compra);
        return compra.toString();
    }
}
