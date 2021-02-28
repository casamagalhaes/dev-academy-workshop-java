package br.com.workshop.helloworld;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RaizQuadradaController {

    @RequestMapping(method = RequestMethod.GET, path = "/raiz-quadrada/{numero}")
    public Map<String, Double> calculaRaizQuadrada(@PathVariable("numero") Long numero) {
        Map<String, Double> mapRetorno = new HashMap<>();
        mapRetorno.put("resultado", calculadoraRaizQuadrada(numero));
        return mapRetorno;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/raiz-quadrada-qs")
    public Double calculaRaizQuadradaQueryString(@RequestParam Long numero) {
        return calculadoraRaizQuadrada(numero);
    }

    @RequestMapping(method = RequestMethod.GET, 
                  path = "/raiz-quadrada-2/{numero}")
    public Double calculaRaizQuadrada2(@PathVariable Long numero) {
        return Math.sqrt(numero.doubleValue());
    }

    private Double calculadoraRaizQuadrada(Long numero) {
        return Math.sqrt(numero.doubleValue());
    }
    
}