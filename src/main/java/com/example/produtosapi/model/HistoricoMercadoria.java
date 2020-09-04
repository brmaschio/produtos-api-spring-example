package com.example.produtosapi.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="HISTORICOMERCADORIA")
public class HistoricoMercadoria {

	private Long historico;
	private Mercadoria mercadoria;
	private BigDecimal valor;
	private Date dataImportado;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name="HISTORICO" )
	public Long getHistorico() {return historico;}
	public void setHistorico(Long historico) {this.historico = historico;}
	
	@ManyToOne
	@JoinColumn(name = "CODIGOMERCADORIA")
	public Mercadoria getMercadoria() {return mercadoria;}
	public void setMercadoria(Mercadoria mercadoria) {this.mercadoria = mercadoria;}
	
	@Column( name="VALOR" )
	public BigDecimal getValor() {return valor;}
	public void setValor(BigDecimal valor) {this.valor = valor;}
	
	@Column( name="DATAIMPORTADO" )
	public Date getDataImportado() {return dataImportado;}
	public void setDataImportado(Date dataImportado) {this.dataImportado = dataImportado;}
	
	public void geradorHistorico(Mercadoria mercadoria) {
		
		this.mercadoria = mercadoria;
		this.valor = mercadoria.getValor();
		this.dataImportado = mercadoria.getDataAtualizacao();
		
	}
	
	
}
