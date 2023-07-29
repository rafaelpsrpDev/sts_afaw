package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	
	public Cidade adicionar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		
		Estado estado = estadoRepository.porId(estadoId);
		
		if (estadoId == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe id relacionado a esse estado %d", estadoId));
		}
		
		cidade.setEstado(estado);
		
		return cidadeRepository.adicionar(cidade);
	}
	
	public void remover(Long cidadeId) {
		try {
			this.cidadeRepository.remover(cidadeId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("%id da cidade não foi encontrado", cidadeId));
		}
	}
	
}
