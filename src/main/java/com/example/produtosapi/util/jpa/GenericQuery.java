package com.example.produtosapi.util.jpa;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GenericQuery {

	@PersistenceContext
	private EntityManager entityManager;
	
	public GenericQueryResult gerarSelectPaginado(String queryStr, String countQuery, Pageable pageable, Class<?> ObjetoClasse) {
		
		GenericQueryResult resultado = new GenericQueryResult();
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
        try {
        	
        	Query query = entityManager.createNativeQuery(queryStr, ObjetoClasse);
    		query.setFirstResult(primeiroRegistroDaPagina);
    		query.setMaxResults(totalRegistrosPorPagina);
    		
    		List<?> registros = query.getResultList();
    		resultado.setRegistros(registros);
    		
        	// Total De Registros
        	query = entityManager.createNativeQuery(countQuery);
        	BigInteger totalDeRegistros = (BigInteger) query.getSingleResult();
        	
        	Integer i = new Integer(totalDeRegistros.intValue());
        	resultado.setTotalRegistros(i);
    		
            return resultado;
            
        } catch (Exception e) {
            throw e;
        }
		
	}
	
	public static class GenericQueryResult {
		
		private List<?> registros;
		private Integer totalRegistros;
		
		public List<?> getRegistros() {return registros;}
		public void setRegistros(List<?> registros) {this.registros = registros;}
		public Integer getTotalRegistros() {return totalRegistros;}
		public void setTotalRegistros(Integer totalRegistros) {this.totalRegistros = totalRegistros;}
		
	}
	
	
}
