package com.example.produtosapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.produtosapi.model.Categoria;
import com.example.produtosapi.repository.CategoriasRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriasResource {

	@Autowired
	private CategoriasRepository repository;
	
	@GetMapping
	public List<Categoria> todasCategorias() {
		
		return repository.findAllByOrderByNome();
		
	}
	
}
