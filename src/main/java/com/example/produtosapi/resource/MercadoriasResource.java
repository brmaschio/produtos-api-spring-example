package com.example.produtosapi.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.produtosapi.model.Mercadoria;
import com.example.produtosapi.repository.MercadoriasRepository;
import com.example.produtosapi.service.MercadoriasService;
import com.example.produtosapi.util.exceptionhandler.UserExceptions;

@RestController
@RequestMapping("/mercadorias")
public class MercadoriasResource {

	@Autowired
	private MercadoriasService service;
	@Autowired
	private MercadoriasRepository repository;
	
	@GetMapping
	public Page<Mercadoria> listarMercadorias(@RequestParam(required = false, defaultValue = "") String produto, Pageable pageable){
		
		return repository.findAllByOrderByProduto(pageable, produto);
		
	}
	
	@PostMapping
	public ResponseEntity<Mercadoria> criar( @Valid @RequestBody Mercadoria mercadoria, HttpServletResponse response ) throws UserExceptions {
		
		Mercadoria mercadoriaSalva = service.salvar(mercadoria);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(mercadoriaSalva);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Mercadoria> atualizar ( @PathVariable Long id, @Valid @RequestBody Mercadoria mercadoria ) throws UserExceptions{
		
		Mercadoria mercadoriaSalvo = service.atualizar(id, mercadoria);
		
		return ResponseEntity.ok(mercadoriaSalvo);
		
	}
	
}
