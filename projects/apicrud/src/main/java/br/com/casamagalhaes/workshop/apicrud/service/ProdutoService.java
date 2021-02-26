package br.com.casamagalhaes.worshop.apicrud.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casamagalhaes.worshop.apicrud.model.Produto;
import br.com.casamagalhaes.worshop.apicrud.repository.ProdutoRepository;

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

}