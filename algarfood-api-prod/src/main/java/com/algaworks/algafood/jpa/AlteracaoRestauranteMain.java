package com.algaworks.algafood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgarfoodApiProdApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class AlteracaoRestauranteMain {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgarfoodApiProdApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
	    RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
	   
	    BigDecimal cTxFrete = new BigDecimal("9.70");
	    
	    Restaurante restaurante = new Restaurante();
	    restaurante.setId(1L);
	    restaurante.setNome("Tai Shan");
	    restaurante.setTaxaFrete(cTxFrete);
	    
	    restauranteRepository.adicionar(restaurante);
	    
		
	}

}
