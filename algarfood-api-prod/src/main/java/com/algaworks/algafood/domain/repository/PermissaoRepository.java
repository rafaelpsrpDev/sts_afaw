package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Permissao;

public interface PermissaoRepository {
	
	public List<Permissao> todos();
	public Permissao porId(Long id);
	public Permissao adicionar(Permissao permissao);
	public void remover(Permissao permissao);
	
}
