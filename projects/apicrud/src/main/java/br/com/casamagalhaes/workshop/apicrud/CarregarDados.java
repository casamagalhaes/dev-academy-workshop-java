package br.com.casamagalhaes.workshop.apicrud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.casamagalhaes.workshop.apicrud.enums.UnidadeMedida;
import br.com.casamagalhaes.workshop.apicrud.model.Produto;
import br.com.casamagalhaes.workshop.apicrud.repository.ProdutoRepository;

@Configuration
public class CarregarDados {
    
    private static final Logger log = 
        LoggerFactory.getLogger(CarregarDados.class);

    @Bean
    CommandLineRunner initDB(ProdutoRepository repository) {
        
        return args -> {
            Produto p;
            for (int i = 0; i < 20; i++) {
                p = new Produto();
                p.setDescricao("Produto " + i);
                p.setDetalhe("Detalhe do Produto " + i);
                p.setForaDeLinha(false);
                p.setUnidadeMedida(UnidadeMedida.values()[i % 4]);
                log.info("Cadastrando produtos... " + repository.save(p));
            }
        };
    }
}
