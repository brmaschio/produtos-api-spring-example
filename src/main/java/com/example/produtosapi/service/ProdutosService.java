package com.example.produtosapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.produtosapi.enums.Status;
import com.example.produtosapi.model.Categoria;
import com.example.produtosapi.model.Produto;
import com.example.produtosapi.repository.CategoriasRepository;
import com.example.produtosapi.repository.ProdutosRepository;
import com.example.produtosapi.util.exceptionhandler.UserExceptions;

@Service
public class ProdutosService {
	
	@Autowired
	private ProdutosRepository repository;
	@Autowired
	private CategoriasRepository categoriasRepository;

	public Produto salvar(Produto produto) throws UserExceptions {
		
		Produto produtoExistente = repository.findByCodigoDeBarras(produto.getCodigoDeBarras());
		
		if(produtoExistente != null) {
			throw new UserExceptions("Codigo De Barras Já Cadastrado");
		}
		
		produto = validarCategoria(produto);
		
		return repository.save(produto);
	}

	public Produto atualizar(Long id, Produto produto) throws UserExceptions {
		
		Produto produtoSalvo = buscaPeloCodigo(id);
		
		BeanUtils.copyProperties(produto, produtoSalvo, "id", "codigoDeBarras");
		
		produto = validarCategoria(produto);
		
		return repository.save(produtoSalvo);
		
	}

	public void deletar(Long codigo) {
		
		Produto produtoSalvo = buscaPeloCodigo(codigo);
		
		produtoSalvo.setStatus(Status.DELETADO);
		
		repository.save(produtoSalvo);
		
	}
	
	private Produto validarCategoria(Produto produto) throws UserExceptions {
		
		Optional<Categoria> categoria = categoriasRepository.findById(produto.getCategoria().getId());
		
		if(!categoria.isPresent()) {
			throw new UserExceptions("Categoria Invalida");
		}
		
		produto.setCategoria(categoria.get());
		
		return produto;
		
	}
	
	private Produto buscaPeloCodigo(Long codigo) {
		
		Optional<Produto> produtoSalvo = repository.findById(codigo);
		
		if(!produtoSalvo.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return produtoSalvo.get();
	}
	
}
