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
import javax.validation.constraints.NotNull;

@Entity
@Table(name="MERCADORIA")
public class Mercadoria {

	private Long id;
	private Mercado mercado = new Mercado();
	private Produto produto = new Produto();
	private BigDecimal valor;
	private char promocao = 'N';
	private boolean habilitado = true;
	private Date dataAtualizacao;
	private Date dataAtualizacaoComp;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name="ID" )
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "CODIGOMERCADO")
	public Mercado getMercado() {return mercado;}
	public void setMercado(Mercado mercado) {this.mercado = mercado;}
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "CODIGOPRODUTO")
	public Produto getProduto() {return produto;}
	public void setProduto(Produto produto) {this.produto = produto;}
	
	@NotNull
	@Column( name="VALOR" )
	public BigDecimal getValor() {return valor;}
	public void setValor(BigDecimal valor) {this.valor = valor;}
	
	@Column( name="PROMOCAO" )
	public char getPromocao() {return promocao;}
	public void setPromocao(char promocao) {this.promocao = promocao;}
	
	@Column( name="HABILITADO" )
	public boolean isHabilitado() {return habilitado;}
	public void setHabilitado(boolean habilitado) {this.habilitado = habilitado;}
	
	@Column( name="DATAATT" )
	public Date getDataAtualizacao() {return dataAtualizacao;}
	public void setDataAtualizacao(Date dataAtualizacao) {this.dataAtualizacao = dataAtualizacao;}
	
	@Column( name="DATAATTCOMP" )
	public Date getDataAtualizacaoComp() {return dataAtualizacaoComp;}
	public void setDataAtualizacaoComp(Date dataAtualizacaoComp) {this.dataAtualizacaoComp = dataAtualizacaoComp;}
	
	public void setDatas(Date data) {
		this.dataAtualizacao = data;
		this.dataAtualizacaoComp = data;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mercadoria other = (Mercadoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Mercadoria [id=" + id + ", valor=" + valor + ", promocao=" + promocao + ", habilitado=" + habilitado
				+ "]";
	}
	
	
	
}
