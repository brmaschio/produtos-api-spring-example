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
	public Page<Mercadoria> findAllByOrderByProduto(Pageable pageable, String produto, String mercado) {
		
		String queryStr = "SELECT T1.* FROM MERCADORIA AS T1\n";
		String countQuery = "SELECT COUNT(T1.ID) FROM MERCADORIA AS T1\n";
		
		String filterQuery = "INNER JOIN PRODUTO AS T2 ON T1.CODIGOPRODUTO = T2.ID\n" + 
				"INNER JOIN MERCADO AS T3 ON T1.CODIGOMERCADO = T3.ID\n" + 
				"WHERE (T3.NOME LIKE '%" +  mercado + "%' OR T3.NOMEFANTASIA LIKE '%" + mercado + "%' ) " + 
				"AND T2.NOME LIKE '%" + produto + "%'";
		
		queryStr = queryStr + filterQuery;
		countQuery = countQuery + filterQuery;
		
		GenericQueryResult resultado = genericQuery.gerarSelectPaginado(queryStr, countQuery, pageable, Mercadoria.class);
		
		List<Mercadoria> mercadorias = (List<Mercadoria>) resultado.getRegistros();
		
		return new PageImpl<>(mercadorias, pageable, resultado.getTotalRegistros());
		
	}

}
