package com.algaworks.algafood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgarfoodApiProdApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class BuscarRestauranteMain {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgarfoodApiProdApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
	    RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
	    
	    Restaurante restaurante = restauranteRepository.porId(1L);
	    
	    System.out.println(restaurante.getNome() + " - " + restaurante.getTaxaFrete());
	    
	    
		
	}

}
