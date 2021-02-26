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
    public ResultadoRaiz calculaRaizQuadrada(@PathVariable("numero") Long numero) {
        // Map<String, Double> mapRetorno = new HashMap<>();
        // mapRetorno.put("resultado", calculadoraRaizQuadrada(numero));
        return new ResultadoRaiz(calculadoraRaizQuadrada(numero));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/raiz-quadrada-qs")
    public Double calculaRaizQuadradaQueryString(@RequestParam Long numero) {
        return calculadoraRaizQuadrada(numero);
    }

    private Double calculadoraRaizQuadrada(Long numero) {
        return Math.sqrt(numero.doubleValue());
    }
    
}

class ResultadoRaiz {
    private Double resultado;
    public ResultadoRaiz(Double resultado) {
        this.resultado = resultado;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }
}
