package com.example.produtosapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.produtosapi.model.Produto;
import com.example.produtosapi.service.ProdutosService;
import com.example.produtosapi.util.exceptionhandler.UserExceptions;

@RestController
@RequestMapping("/produtos")
@PreAuthorize("hasAuthority('ROLE_PRODUTO') and #oauth2.hasScope('admin')")
public class ProdutosCompResource {

	@Autowired
	private ProdutosService service;
	
	@PutMapping("/status/{id}")
	public ResponseEntity<Produto> atualizarStatus ( @PathVariable Long id ) throws UserExceptions{
		
		Produto produtoSalvo = service.atualizarStatus(id);
		
		return ResponseEntity.ok(produtoSalvo);
	}
	
}
