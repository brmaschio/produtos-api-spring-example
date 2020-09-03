package com.example.produtosapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.produtosapi.model.Mercadoria;

public interface MercadoriasRepositoryQuery {

	Page<Mercadoria> findAllByOrderByProduto(Pageable pageable, String produto);
	
}
