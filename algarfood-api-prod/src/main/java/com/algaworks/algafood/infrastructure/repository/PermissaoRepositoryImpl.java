package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository{

	@PersistenceContext
	private EntityManager entityManager;	
	
	@Override
	public List<Permissao> todos() {
		return entityManager.createQuery("from Permissao", Permissao.class)
				.getResultList();
	}

	@Override
	public Permissao porId(Long id) {
		return entityManager.find(Permissao.class, id);
	}

	@Transactional
	@Override
	public Permissao adicionar(Permissao permissao) {
		return entityManager.merge(permissao);
	}
	
	@Transactional
	@Override
	public void remover(Permissao permissao) {
		permissao = this.porId(permissao.getId());
		entityManager.remove(permissao);
	}
	
	
}
