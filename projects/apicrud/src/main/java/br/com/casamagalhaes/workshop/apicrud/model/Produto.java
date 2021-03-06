package br.com.casamagalhaes.workshop.apicrud.model;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.casamagalhaes.workshop.apicrud.enums.UnidadeMedida;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_produto")
    private Long id;

    @NotEmpty(message = "descrição é obrigatória")
    @Size(min = 4, max = 100, message = "descrição deve ter no mínimo 4 e no máximo 50 caracteres")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private UnidadeMedida unidadeMedida;

    private String detalhe;
    
    @Column(name="fora_de_linha")
    private Boolean foraDeLinha;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public Boolean getForaDeLinha() {
        return foraDeLinha;
    }

    public void setForaDeLinha(Boolean foraDeLinha) {
        this.foraDeLinha = foraDeLinha;
    }

}