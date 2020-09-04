package com.example.produtosapi.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.produtosapi.model.HistoricoMercadoria;
import com.example.produtosapi.model.Mercado;
import com.example.produtosapi.model.Mercadoria;
import com.example.produtosapi.model.Produto;
import com.example.produtosapi.repository.HistoricosMercadoriasRepository;
import com.example.produtosapi.repository.MercadoriasRepository;
import com.example.produtosapi.repository.MercadosRepository;
import com.example.produtosapi.repository.ProdutosRepository;
import com.example.produtosapi.util.exceptionhandler.UserExceptions;

@Service
public class MercadoriasService {

	@Autowired
	private MercadoriasRepository repository;
	@Autowired
	private ProdutosRepository produtoRepository;
	@Autowired
	private MercadosRepository mercadoRepository;
	@Autowired
	private HistoricosMercadoriasRepository historicoRepository;
	
	public Mercadoria salvar(Mercadoria mercadoria) throws UserExceptions {
		
		Mercadoria mercadoriaExistente = repository.findByMercadoAndProduto(mercadoria.getMercado().getId(), mercadoria.getProduto().getId());
		
		if(mercadoriaExistente != null) {
			throw new UserExceptions("Mercadoria Existente");
		}
		
		mercadoria.setDatas(new Date());
		
		// Evita A Atualização Dos Campos De Produto e Mercado Pelo Corpo Do JSON, E Valida Se Os Mesmos Existem
		
		Optional<Mercado> mercadoSalvo = mercadoRepository.findById(mercadoria.getMercado().getId());
		Optional<Produto> produtoSalvo = produtoRepository.findById(mercadoria.getProduto().getId());
		
		if(!produtoSalvo.isPresent()) {
			throw new UserExceptions("Produto Invalido");
		}
		if(!mercadoSalvo.isPresent()) {
			throw new UserExceptions("Mercado Invalido");
		}
		
		mercadoria.setMercado(mercadoSalvo.get());
		mercadoria.setProduto(produtoSalvo.get());
		
		gerarHistorico(mercadoria);
		
		return repository.save(mercadoria);
		
	}

	public Mercadoria atualizar(Long id, Mercadoria mercadoria) throws UserExceptions {
		
		Mercadoria mercadoriaSalvo = buscaPeloCodigo(id);
		
		BeanUtils.copyProperties(mercadoria, mercadoriaSalvo, "id", "mercado", "produto");
		
		mercadoriaSalvo.setDatas(new Date());
		
		gerarHistorico(mercadoriaSalvo);
		
		return repository.save(mercadoriaSalvo);
	
	}
	
	public Mercadoria atualizarStatus(Long id) {
		
		Mercadoria mercadoriaSalvo = buscaPeloCodigo(id);
		
		mercadoriaSalvo.habilitaDesabilita();
		
		return repository.save(mercadoriaSalvo);
	}
	
	private void gerarHistorico(Mercadoria mercadoria) {
		
		HistoricoMercadoria historico = new HistoricoMercadoria();
		historico.geradorHistorico(mercadoria);
		
		historicoRepository.save(historico);
		
	}
	
	private Mercadoria buscaPeloCodigo(Long codigo) {
		
		Optional<Mercadoria> mercadoriaSalva = repository.findById(codigo);
		
		if(!mercadoriaSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return mercadoriaSalva.get();
	}
	
}
