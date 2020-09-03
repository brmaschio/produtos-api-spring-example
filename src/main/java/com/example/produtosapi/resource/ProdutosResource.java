package com.example.produtosapi.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.example.produtosapi.model.Produto;
import com.example.produtosapi.repository.ProdutosRepository;
import com.example.produtosapi.service.ProdutosService;
import com.example.produtosapi.util.exceptionhandler.UserExceptions;

@RestController
@RequestMapping("/produtos")
public class ProdutosResource {

	@Autowired
	private ProdutosService service;
	@Autowired
	private ProdutosRepository repository;
	
	@GetMapping
	public Page<Produto> listarProdutos(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable){
		
		return repository.findByNomeAndNotDeletado(nome, pageable);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPeloCodigo( @PathVariable Long id ) {
				
		Produto produto = repository.findByIdAndNotDeletado(id);
		
		if(produto == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(produto);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Produto> criar( @Valid @RequestBody Produto produto, HttpServletResponse response ) throws UserExceptions {
		
		Produto produtoSalvo = service.salvar(produto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar ( @PathVariable Long id, @Valid @RequestBody Produto produto ) throws UserExceptions{
		
		Produto produtoSalvo = service.atualizar(id, produto);
		
		return ResponseEntity.ok(produtoSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover( @PathVariable Long codigo ) {
		
		service.deletar(codigo);
	}
	
}
