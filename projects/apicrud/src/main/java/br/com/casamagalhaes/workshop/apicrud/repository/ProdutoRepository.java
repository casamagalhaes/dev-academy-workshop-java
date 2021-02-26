package br.com.casamagalhaes.workshop.apicrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.casamagalhaes.workshop.apicrud.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}