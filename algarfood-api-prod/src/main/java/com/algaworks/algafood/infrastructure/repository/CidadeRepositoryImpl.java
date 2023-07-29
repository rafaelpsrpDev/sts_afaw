package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Cidade> todos() {
		return entityManager.createQuery("from Cidade", Cidade.class)
				.getResultList();
	}

	@Override
	public Cidade porId(Long id) {
		return entityManager.find(Cidade.class, id);
	}
	
	@Transactional
	@Override
	public Cidade adicionar(Cidade cidade) {
		return entityManager.merge(cidade);
	}

	@Transactional
	@Override
	public void remover(Long cidadeId) {
		Cidade cidade = this.porId(cidadeId);
		
		if(cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		this.entityManager.remove(cidade);
	}
	
}
