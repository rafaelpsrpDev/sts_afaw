package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Cozinha> todos() {		
		return entityManager.createQuery("from Cozinha", Cozinha.class)
				.getResultList();
	}

	@Override
	public Cozinha porId(Long id) {
		return entityManager.find(Cozinha.class, id);
	}
	

	@Override
	@Transactional
	public Cozinha adicionar(Cozinha cozinha) {
		return entityManager.merge(cozinha);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		Cozinha cozinha = this.porId(id);
		if(cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		entityManager.remove(cozinha);
	}
	
}
