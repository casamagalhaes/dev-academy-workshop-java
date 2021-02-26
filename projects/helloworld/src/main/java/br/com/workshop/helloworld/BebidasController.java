package br.com.workshop.helloworld;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BebidasController {

    List<String> bebidas = new ArrayList<String>();

    @RequestMapping(method = RequestMethod.GET, path="/bebidas")
    public List<String> buscaTodas() {
        return bebidas;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/bebidas")
    public void salvar(@RequestBody String nomeBebida) {
        bebidas.add(nomeBebida);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/bebidas/{nomeBebida}")
    public void remove(@PathVariable String nomeBebida) {
        bebidas.remove(nomeBebida);
    }
    
}
