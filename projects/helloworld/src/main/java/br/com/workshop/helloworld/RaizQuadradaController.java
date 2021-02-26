package br.com.workshop.helloworld;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RaizQuadradaController {

    @RequestMapping(method = RequestMethod.GET, path = "/raiz-quadrada/{numero}")
    public Long calculaRaizQuadrada(@PathVariable Long numero) {
        return numero * numero;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/raiz-quadrada-qs")
    public Long calculaRaizQuadradaQueryString(@RequestParam Long numero) {
        return numero * numero;
    }
    
}
