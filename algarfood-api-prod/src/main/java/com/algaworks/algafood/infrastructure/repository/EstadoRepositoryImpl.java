package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Estado> todos() {
		return entityManager.createQuery("from Estado", Estado.class)
				.getResultList();
	}

	@Override
	public Estado porId(Long id) {
		return entityManager.find(Estado.class, id);
	}

	@Transactional
	@Override
	public Estado adicionar(Estado estado) {
		return entityManager.merge(estado);
	}

	@Transactional
	@Override
	public void remover(Long estadoId) {
		Estado estado = this.porId(estadoId);
		
		if(estado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		entityManager.remove(estado);
	}
	

}
