package com.example.produtosapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.produtosapi.enums.Status;
import com.example.produtosapi.model.Mercado;
import com.example.produtosapi.repository.MercadosRepository;
import com.example.produtosapi.util.exceptionhandler.UserExceptions;

@Service
public class MercadosService {

	@Autowired
	private MercadosRepository repository;
	
	public Mercado salvar(Mercado mercado) throws UserExceptions {
		
		Mercado buscaMercado = repository.findByCnpj(mercado.getCnpj());
		
		if(buscaMercado != null) {
			throw new UserExceptions("CNPJ Já Cadastrado");
		}
		
		return repository.save(mercado);
		
	}
	
	public Mercado atualizar(Long id, Mercado mercado) {
		
		Mercado mercadoSalvo = buscaPeloCodigo(id);
		
		BeanUtils.copyProperties(mercado, mercadoSalvo, "id", "cnpj");
		
		return repository.save(mercadoSalvo);
	
	}
	
	public void deletarMercado(Long codigo) {
				
		Mercado mercadoSalvo = buscaPeloCodigo(codigo);
		
		mercadoSalvo.setStatus(Status.DELETADO);
		
		repository.save(mercadoSalvo);
		
	}
	
	public Mercado atualizarStatus(Long id) {
		
		Mercado mercadoSalvo = buscaPeloCodigo(id);
		mercadoSalvo.habilitaDesabilita();
		
		return repository.save(mercadoSalvo);
	}
	
	private Mercado buscaPeloCodigo(Long codigo) {
		
		Optional<Mercado> mercadoSalvo = repository.findById(codigo);
		
		if(!mercadoSalvo.isPresent() || mercadoSalvo.get().getStatus().equals(Status.DELETADO)) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return mercadoSalvo.get();
	}

	
}
