package com.example.produtosapi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="CATEGORIA")
public class Categoria {

	private Long id;
	private String nome;
	private String imagem;
	private List<Produto> produtos;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name="ID" )
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	@NotNull
	@NotBlank
	@Column( name="NOME" )
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	
	@Column( name="IMAGEM" )
	public String getImagem() {return imagem;}
	public void setImagem(String imagem) {this.imagem = imagem;}
	
	@Transient
	public List<Produto> getProdutos() {return produtos;}
	public void setProdutos(List<Produto> produtos) {this.produtos = produtos;}
	
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
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
