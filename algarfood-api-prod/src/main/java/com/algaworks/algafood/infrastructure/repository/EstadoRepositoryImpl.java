package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Component
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
	public void remover(Estado estado) {
		estado = this.porId(estado.getId());
		entityManager.remove(estado);
	}

}
