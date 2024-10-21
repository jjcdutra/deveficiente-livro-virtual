package com.jjcdutra.livro_virtual.pagamento;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
public class PagamentoController {

    @Autowired
    private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(estadoPertenceAPaisValidator);
    }

    @PostMapping
    public String criar(@RequestBody @Valid NovaCompraRequest request) {
        return request.toString();
    }
}
