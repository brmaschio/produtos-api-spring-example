package com.example.produtosapi.util.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InternalExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
		
	@ExceptionHandler(UserExceptions.class)
    public ResponseEntity<Object> userExceptionsHandle
    	(Exception ex, HttpServletRequest request, HttpServletResponse response) {
    	
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	
		String mensagemErro = ex.getMessage();

		List<Erro> erros = Arrays.asList(new Erro(mensagemErro, ""));
		
		return ResponseEntity.status(status).body(erros);
			
    }
	
	// Para @NotNull, @NotBlank no model
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros = criarListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String mensagem = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String detalhe = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagem, detalhe));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	
	/*
	 * Funções Auxiliares
	 */
	
	private List<Erro> criarListaDeErros(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String detalhe = fieldError.toString();
			erros.add(new Erro(mensagem, detalhe));
		}
			
		return erros;
	}
	
	/*
	 * Classe Auxiliar
	 */
	
	public static class Erro {
		
		private String mensagemErro;
		private String detalheErro;
		
		public Erro(String mensagemErro, String detalheErro) {
			this.mensagemErro = mensagemErro;
			this.detalheErro = detalheErro;
		}

		public String getMensagemErro() {return mensagemErro;}
		public String getDetalheErro() {return detalheErro;}

		
	}
	
}
