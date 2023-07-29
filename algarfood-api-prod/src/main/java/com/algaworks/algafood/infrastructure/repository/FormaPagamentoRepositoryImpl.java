package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

@Repository
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<FormaPagamento> todos() {
		return entityManager.createQuery("from FormaPagamento", FormaPagamento.class)
				.getResultList();
	}

	@Override
	public FormaPagamento porId(Long id) {
		return entityManager.find(FormaPagamento.class, id);
	}
	
	@Transactional
	@Override
	public FormaPagamento adicionar(FormaPagamento formaPagamento) {
		return entityManager.merge(formaPagamento);
	}

	@Transactional
	@Override
	public void remover(FormaPagamento formaPagamento) {
		formaPagamento = this.porId(formaPagamento.getId());
		entityManager.remove(formaPagamento);
	}


}
