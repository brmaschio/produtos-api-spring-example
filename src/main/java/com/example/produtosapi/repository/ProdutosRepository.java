package com.example.produtosapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.produtosapi.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {

	Produto findByCodigoDeBarras(String codigoDeBarras);
	
	@Query(value = "SELECT * FROM PRODUTO WHERE NOME LIKE %:nome% AND STATUS <> 'DELETADO' ORDER BY NOME", nativeQuery = true)
	Page<Produto> findByNomeAndNotDeletado(@Param("nome") String nome, Pageable pageable);

	@Query(value = "SELECT * FROM PRODUTO WHERE ID = :mercadoId AND STATUS <> 'DELETADO'", nativeQuery = true)
	Produto findByIdAndNotDeletado(@Param("mercadoId") Long id);

}
