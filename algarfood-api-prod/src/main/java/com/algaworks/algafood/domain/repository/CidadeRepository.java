package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Cidade;

public interface CidadeRepository {
	
	public List<Cidade> todos();
	public Cidade porId(Long id);
	public Cidade adicionar(Cidade cidade);
	public void remover(Long cidadeId);
}
