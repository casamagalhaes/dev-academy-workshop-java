package br.com.casamagalhaes.workshop.apicrud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.casamagalhaes.workshop.apicrud.model.Produto;
import br.com.casamagalhaes.workshop.apicrud.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produto")
@RestController
@RequestMapping(path = "/produtos",  
  consumes = MediaType.APPLICATION_JSON_VALUE, 
  produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {
    
    @Autowired
    private ProdutoService service;

    @GetMapping({"/", ""})
    public List<Produto> listarTodos() {
        return service.findAll();
    }

    @GetMapping({"/paginada"})
    public Page<Produto> listarTodos(@RequestParam Integer numeroPagina, 
        @RequestParam Integer tamanhoPagina) {
        return service.listarTodosPaginado(numeroPagina, tamanhoPagina);
    }

    @GetMapping("/{id}")
    public Produto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/pesquisa")
    public List<Produto> listarTodos(@RequestParam String descricao) {
        return service.findByDescricao(descricao);
    }

    @PostMapping({"/", ""})
    @ResponseStatus(code = HttpStatus.CREATED)
    public Produto salvar(@Valid @RequestBody Produto produto) {
        return service.save(produto);
    }

    @PostMapping(path = "/find", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE) 
    public List<Produto> listarPeloExemplo(@RequestBody Produto produto) {
        return service.listarPeloExemplo(produto);
    }

    @PutMapping("/{id}")
    public Produto atualiza(@PathVariable Long id, @RequestBody Produto produto) {
        return service.update(id, produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        service.remove(id);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void notFound() {}

    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public void unsupport(){}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }    
    

}
