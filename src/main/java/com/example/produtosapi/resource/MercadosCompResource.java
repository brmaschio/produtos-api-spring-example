package com.example.produtosapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.produtosapi.model.Mercado;
import com.example.produtosapi.service.MercadosService;

@RestController
@RequestMapping("/mercados")
@PreAuthorize("hasAuthority('ROLE_MERCADO') and #oauth2.hasScope('admin')")
public class MercadosCompResource {

	@Autowired
	private MercadosService service;
	
	@PutMapping("/status/{id}")
	public ResponseEntity<Mercado> atualizarStatus ( @PathVariable Long id ){
		
		Mercado mercadoSalvo = service.atualizarStatus(id);
		
		return ResponseEntity.ok(mercadoSalvo);
	}
	
}
