package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Restaurante> todas() {
		return entityManager.createQuery("from Restaurante", Restaurante.class)
				.getResultList();
	}

	@Override
	public Restaurante porId(Long id) {
		return entityManager.find(Restaurante.class, id);
	}

	@Transactional
	@Override
	public Restaurante adicionar(Restaurante restaurante) {
		return entityManager.merge(restaurante);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		Restaurante restaurante = this.porId(id);
		
		if(restaurante == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		entityManager.remove(restaurante);
	}

}
