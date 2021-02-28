package br.com.workshop.helloworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BebidasController {

    List<String> bebidas = new ArrayList<String>();

    /**
     * Retorna todas as bebidas
     * @param nomeBebida
     */
    @RequestMapping(method = RequestMethod.GET, path="/bebidas")
    public List<String> buscaTodas() {
        return bebidas;
    }

    /**
     * Salva uma bebida recebendo uma String(Text) como parâmetro
     * @param nomeBebida
     */
    @RequestMapping(method = RequestMethod.POST, path = "/bebidas")
    public void salvar(@RequestBody String nomeBebida) {
        bebidas.add(nomeBebida);
    }

    /**
     * Remove uma bebida recebendo os dados como texto
     * @param nomeBebida
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/bebidas/{nomeBebida}")
    public void remove(@PathVariable String nomeBebida) {
        bebidas.remove(nomeBebida);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/bebidas/{nomeBebida}")
    public void atualiza(@RequestBody String novoNomeBebida, @PathVariable String nomeBebida) {
        bebidas.remove(nomeBebida);
        bebidas.add(novoNomeBebida);
    }
    

    /** 
     *  ------- USANDO JSON --------
     * 
     * Salva uma bebida recebendo os dados como JSON
     * @param bebida
     */
    @RequestMapping(method = RequestMethod.POST, path = "/bebidas-json")
    public void salvaComJson(@RequestBody Map<String, String> bebida) {
        String nomeBebida = bebida.get("nome");
        bebidas.add(nomeBebida);
    }
}
