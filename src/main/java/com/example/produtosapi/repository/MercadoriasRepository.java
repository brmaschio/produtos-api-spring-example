package com.example.produtosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.produtosapi.model.Mercadoria;

public interface MercadoriasRepository extends JpaRepository<Mercadoria, Long>, MercadoriasRepositoryQuery {

	@Query(value = "SELECT * FROM MERCADORIA WHERE CODIGOMERCADO = :mercadoId AND CODIGOPRODUTO = :produtoId", nativeQuery = true)
	Mercadoria findByMercadoAndProduto(@Param("mercadoId") Long mercadoId, @Param("produtoId") Long produtoId);
	
}
