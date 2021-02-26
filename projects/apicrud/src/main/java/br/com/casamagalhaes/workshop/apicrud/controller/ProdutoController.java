package br.com.casamagalhaes.workshop.apicrud.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.casamagalhaes.workshop.apicrud.model.Produto;
import br.com.casamagalhaes.workshop.apicrud.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping({"/", ""})
    public List<Produto> listaTodos() {
        return service.findAll();
    }

    @GetMapping({"/{id}", "/{id}/"})    
    public Produto obter(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping({"/", ""})
    public Produto salvar( @RequestBody Produto produto) {
        return service.save(produto);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public Produto atualizar(@PathVariable("id") Long id, @RequestBody Produto produto) {
        return service.update(id, produto);
    }
    
    @ResponseStatus(value=HttpStatus.NOT_FOUND,
                  reason="Produto não existe")  // 404
    @ExceptionHandler(EntityNotFoundException.class)
    public void notFound() {}

    @ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY ,
                  reason="Falha ao processar entidade da requisição")  // 422
    @ExceptionHandler(UnsupportedOperationException.class)
    public void unsupported() {}


}