package com.example.produtosapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.produtosapi.model.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Long> {

	List<Categoria> findAllByOrderByNome();
	
}
