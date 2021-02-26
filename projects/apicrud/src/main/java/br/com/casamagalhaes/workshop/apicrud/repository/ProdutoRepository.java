package br.com.casamagalhaes.workshop.apicrud.repository;

import br.com.casamagalhaes.workshop.apicrud.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}