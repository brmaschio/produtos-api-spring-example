package com.example.produtosapi.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.produtosapi.model.Mercadoria;
import com.example.produtosapi.util.jpa.GenericQuery;
import com.example.produtosapi.util.jpa.GenericQuery.GenericQueryResult;

public class MercadoriasRepositoryImpl implements MercadoriasRepositoryQuery {

	/**	
	 * 
	 * Apenas outra abordagem 
	 * 
	 * */
	
	@Autowired
	private GenericQuery genericQuery;

	@SuppressWarnings("unchecked")
	@Override
	public Page<Mercadoria> findAllByOrderByProduto(Pageable pageable, String produto) {
		
		String queryStr = "SELECT T1.* FROM MERCADORIA AS T1 INNER JOIN PRODUTO AS T2 ON T1.CODIGOPRODUTO = T2.ID";
		String countQuery = "SELECT COUNT(T1.ID) FROM MERCADORIA AS T1 INNER JOIN PRODUTO AS T2 ON T1.CODIGOPRODUTO = T2.ID";
		
		GenericQueryResult resultado = genericQuery.gerarSelectPaginado(queryStr, countQuery, pageable, Mercadoria.class);
		
		List<Mercadoria> mercadorias = (List<Mercadoria>) resultado.getRegistros();
		
		return new PageImpl<>(mercadorias, pageable, resultado.getTotalRegistros());
		
	}

}
