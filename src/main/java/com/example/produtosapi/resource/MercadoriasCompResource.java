package com.example.produtosapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.produtosapi.model.Mercadoria;
import com.example.produtosapi.service.MercadoriasService;

@RestController
@RequestMapping("/mercadorias")
public class MercadoriasCompResource {

	@Autowired
	private MercadoriasService service;
	
	@PutMapping("/status/{id}")
	public ResponseEntity<Mercadoria> atualizarStatus ( @PathVariable Long id ) {
		
		Mercadoria mercadoria = service.atualizarStatus(id);
		
		return ResponseEntity.ok(mercadoria);
		
	}
	
}
