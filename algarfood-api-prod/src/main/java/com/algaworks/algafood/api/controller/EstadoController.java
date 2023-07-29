package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.todos();
	}
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
		Estado estado = this.estadoRepository.porId(estadoId);
		
		if (estado == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.ok(estado);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado) {
		return this.cadastroEstadoService.adicionar(estado);
	}
	
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
			
		Estado estadoNovo = this.estadoRepository.porId(estadoId);
		
		if (estadoNovo == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(estado, estadoNovo, "id");
		
		estadoNovo = this.cadastroEstadoService.adicionar(estadoNovo);
		
		return ResponseEntity.ok(estadoNovo);
	}
	
	@PatchMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizarParcial(@PathVariable Long estadoId, @RequestBody Map<String, Object> camposOrigem) {
		
		Estado estadoNovo = this.estadoRepository.porId(estadoId);
		
		if (estadoNovo == null) {
			return ResponseEntity.notFound().build();
		}
		
		this.merge(camposOrigem, estadoNovo);
		
		return this.atualizar(estadoId, estadoNovo);
	}
	
	private void merge(Map<String, Object> camposDestino, Estado estadoDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Estado estadoOrigem = objectMapper.convertValue(camposDestino, Estado.class);
		
		camposDestino.forEach((nomeProprieadade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Estado.class, nomeProprieadade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, estadoOrigem);
			
			ReflectionUtils.setField(field, estadoDestino, novoValor);
			
		});
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {
		try {
			this.cadastroEstadoService.remover(estadoId);
			return ResponseEntity.noContent().build();
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
