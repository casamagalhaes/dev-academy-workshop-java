package br.com.casamagalhaes.workshop.apicrud.test.api;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.casamagalhaes.workshop.apicrud.enums.UnidadeMedida;
import br.com.casamagalhaes.workshop.apicrud.model.Produto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
public class ProdutoControllerTest {

     @Value("${server.port}")
     private int porta;

     private RequestSpecification requisicao;

     private ObjectMapper objectMapper = new ObjectMapper();


     @BeforeEach
     private void preperarRequisicao(){
        requisicao = new RequestSpecBuilder()
           .setBasePath("/produtos")
           .setPort(porta)
           .setAccept(ContentType.JSON)
           .setContentType(ContentType.JSON)
           .log(LogDetail.ALL)
           .build();
     }


     @Test
     public void deveriaReceberMensagemDeOk(){
         given()
          .spec(requisicao)
        .expect()   
          .statusCode(HttpStatus.SC_OK)  
        .when()  
          .get();
     }

     @Test
     public void deveriaCriarUmProduto() throws JsonProcessingException {
        Produto produtoCadastrado =
        given()
          .spec(requisicao)
          .body(objectMapper.writeValueAsString(dadoUmProduto()))
        .when()  
          .post()
        .then()
          .statusCode(HttpStatus.SC_CREATED)
        .extract()
          .as(Produto.class);    
        
        assertNotNull(produtoCadastrado, "produto não foi cadastrado");    
        assertNotNull(produtoCadastrado.getId(), "id do produto não gerado");       
     }

     @Test
     public void naoDeveriaGravarProdutoSemDescricao() throws JsonProcessingException {
       given()
         .spec(requisicao)
         .body(objectMapper.writeValueAsString(dadoUmProdutoSemDescricao()))
       .when()  
         .post()
       .then()
         .statusCode(HttpStatus.SC_BAD_REQUEST);    
     }

     @Test
     public void deveriaAlterarUmProduto() throws JsonProcessingException {
       Produto produtoCadastrado =
        given()
          .spec(requisicao)
          .body(objectMapper.writeValueAsString(dadoUmProduto()))
        .when()  
          .post()
        .then()
          .statusCode(HttpStatus.SC_CREATED)
        .extract()
          .as(Produto.class);    
        
        assertNotNull(produtoCadastrado, "produto não foi cadastrado");    
        assertNotNull(produtoCadastrado.getId(), "id do produto não gerado");

        produtoCadastrado.setDescricao("produto alterado");

        Produto produtoaAlterado =
        given()
          .spec(requisicao)
          .body(objectMapper.writeValueAsString(produtoCadastrado))
        .when()
          .put("/{id}", produtoCadastrado.getId())
        .then() 
          .statusCode(HttpStatus.SC_OK)
        .extract()
          .as(Produto.class);
          
        assertEquals(produtoCadastrado.getDescricao(), produtoaAlterado.getDescricao(),
          "descrição não foi alterada");  
     }

     @Test
     public void deveriaExcluirUmProduto() throws JsonProcessingException {

       Produto produtoCadastrado =
        given()
          .spec(requisicao)
          .body(objectMapper.writeValueAsString(dadoUmProduto()))
        .when()  
          .post()
        .then()
          .statusCode(HttpStatus.SC_CREATED)
        .extract()
          .as(Produto.class);    
        
        assertNotNull(produtoCadastrado, "produto não foi cadastrado");    
        assertNotNull(produtoCadastrado.getId(), "id do produto não gerado");

        given()
          .spec(requisicao)
        .when()
          .delete("/{id}", produtoCadastrado.getId())
        .then() 
          .statusCode(HttpStatus.SC_NO_CONTENT);

        given()
          .spec(requisicao)
          .body(objectMapper.writeValueAsString(produtoCadastrado))
        .when()
          .get("/{id}", produtoCadastrado.getId())
        .then() 
          .statusCode(HttpStatus.SC_NOT_FOUND);

     }



     private Produto dadoUmProduto(){
        Produto produto = new Produto();
        produto.setDescricao("coca cola");
        produto.setUnidadeMedida(UnidadeMedida.LITRO);
        produto.setDetalhe("refrigerante");
        produto.setForaDeLinha(Boolean.FALSE);
        return produto;
     }

     private Produto dadoUmProdutoSemDescricao(){
        Produto produto = new Produto();
        produto.setUnidadeMedida(UnidadeMedida.UNIDADE);
        produto.setDetalhe("carne");
        produto.setForaDeLinha(Boolean.FALSE);
        return produto;
   }
   
}