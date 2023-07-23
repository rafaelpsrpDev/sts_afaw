package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;

public interface CozinhaRepository {
	
	public List<Cozinha> todos();
	public Cozinha porId(Long id);
	public Cozinha adicionar(Cozinha cozinha);
	public void remover(Cozinha cozinha);
}
