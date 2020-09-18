package com.example.produtosapi.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.produtosapi.model.Mercado;
import com.example.produtosapi.repository.MercadosRepository;
import com.example.produtosapi.service.MercadosService;
import com.example.produtosapi.util.exceptionhandler.UserExceptions;

@RestController
@RequestMapping("/mercados")
@PreAuthorize("hasAuthority('ROLE_MERCADO') and #oauth2.hasScope('admin')")
public class MercadosResource {

	@Autowired
	private MercadosRepository repository;
	@Autowired
	private MercadosService service;
	
	@GetMapping
	public Page<Mercado> listarMercados(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable){
		
		return repository.findByNomeAndNotDeletado(nome, pageable);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mercado> buscarPeloCodigo( @PathVariable Long id ) {
				
		Mercado mercado = repository.findByIdAndNotDeletado(id);
		
		if(mercado == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(mercado);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Mercado> criar( @Valid @RequestBody Mercado mercado, HttpServletResponse response ) throws UserExceptions {
		
		Mercado mercadoSalvo = service.salvar(mercado);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(mercadoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Mercado> atualizar ( @PathVariable Long id, @Valid @RequestBody Mercado mercado ){
		
		Mercado mercadoSalvo = service.atualizar(id, mercado);
		
		return ResponseEntity.ok(mercadoSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover( @PathVariable Long codigo ) {
		service.deletarMercado(codigo);
	}
	
}
