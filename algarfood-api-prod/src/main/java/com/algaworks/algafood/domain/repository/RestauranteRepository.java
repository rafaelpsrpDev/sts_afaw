package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Restaurante;

public interface RestauranteRepository {
	
	public List<Restaurante> todas();
	public Restaurante porId(Long id);
	public Restaurante adicionar(Restaurante restaurante);
	public void remover (Long id);
}
