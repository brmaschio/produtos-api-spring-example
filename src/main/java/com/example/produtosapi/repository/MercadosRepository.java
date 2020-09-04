package com.example.produtosapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.produtosapi.model.Mercado;

public interface MercadosRepository extends JpaRepository<Mercado, Long> {

	Mercado findByCnpj(String cnpj);

	@Query(value = "SELECT * FROM MERCADO WHERE NOME LIKE %:nome% AND STATUS <> 'DELETADO' ORDER BY NOME", nativeQuery = true)
	Page<Mercado> findByNomeAndNotDeletado(@Param("nome") String nome, Pageable pageable);
	
	@Query(value = "SELECT * FROM MERCADO WHERE ID = :mercadoId AND STATUS <> 'DELETADO'", nativeQuery = true)
	Mercado findByIdAndNotDeletado(@Param("mercadoId") Long id);
	
}
