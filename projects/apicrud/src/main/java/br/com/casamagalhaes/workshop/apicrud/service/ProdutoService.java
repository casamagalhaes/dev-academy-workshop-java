package br.com.casamagalhaes.workshop.apicrud.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.casamagalhaes.workshop.apicrud.model.Produto;
import br.com.casamagalhaes.workshop.apicrud.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto findById(Long id) {        
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

	public List<Produto> findAll() {
		return repository.findAll();
	}

    public List<Produto> findByDescricao(String descricao) {
        return repository.findByDescricao2(descricao);
    }

    public Page<Produto> listarTodosPaginado(
            Integer numeroPagina, 
            Integer tamanhoPagina) {
        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return repository.findAll(pageable);
    }

    public Produto save(Produto produto) {
        return repository.saveAndFlush(produto);
    }

    public Produto update(Long id, Produto produto) {
        if (repository.existsById(id)) {
            if (id.equals(produto.getId()))
                return repository.saveAndFlush(produto);
            else
                throw new UnsupportedOperationException("Id informado diferente do Produto.");
        } else
            throw new EntityNotFoundException("Produto id: " + produto.getId());
    }


    public List<Produto> listarPeloExemplo(Produto produto) {

        return repository.findAll(Example.of(produto, ExampleMatcher.matchingAny()));
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

}