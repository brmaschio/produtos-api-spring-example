package com.example.produtosapi.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.produtosapi.enums.Status;

@Entity
@Table(name="MERCADO")
public class Mercado {

	private Long id;
	private String cnpj;
	private String nome;
	private String nomeFantasia;
	private Status status = Status.ATIVO;
	private Logradouro logradouro;
	private String imagem;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name="ID" )
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	@NotNull
	@NotBlank
	@Column( name="CNPJ" )
	public String getCnpj() {return cnpj;}
	public void setCnpj(String cnpj) {this.cnpj = cnpj;}
	
	@NotNull
	@NotBlank
	@Column( name="NOME" )
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	
	@NotNull
	@NotBlank
	@Column( name="NOMEFANTASIA" )
	public String getNomeFantasia() {return nomeFantasia;}
	public void setNomeFantasia(String nomeFantasia) {this.nomeFantasia = nomeFantasia;}
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column( name="STATUS" )
	public Status getStatus() {return status;}
	public void setStatus(Status status) {this.status = status;}
	
	@Embedded
	public Logradouro getLogradouro() {return logradouro;}
	public void setLogradouro(Logradouro logradouro) {this.logradouro = logradouro;}
	
	@Column( name="IMAGEM" )
	public String getImagem() {return imagem;}
	public void setImagem(String imagem) {this.imagem = imagem;}
	
	public void habilitaDesabilita() {
		
		this.status = this.status.equals(Status.ATIVO) ? Status.INATIVO : Status.ATIVO;
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
		Mercado other = (Mercado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Mercado [id=" + id + ", cnpj=" + cnpj + ", nome=" + nome + ", nomeFantasia=" + nomeFantasia
				+ ", status=" + status + ", imagem=" + imagem + "]";
	}
	
	
	
	
	
}
