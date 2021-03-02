package br.com.casamagalhaes.workshop.apicrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.casamagalhaes.workshop.apicrud.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    List<Produto> findByDescricaoContains(String descricao);

    @Query("SELECT p FROM Produto p where p.descricao like ?1% ")
    List<Produto> findByDescricao2(String descricao);
}