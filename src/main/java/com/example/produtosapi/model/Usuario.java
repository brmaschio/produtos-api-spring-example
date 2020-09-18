package com.example.produtosapi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import javax.persistence.JoinColumn;

@Entity
@Table(name="USUARIO")
public class Usuario {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private List<Permissao> permissoes;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name="ID" )
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	@Column( name="NOME" )
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	
	@Column( name="EMAIL" )
	@NotNull
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	@Column( name="SENHA" )
	@NotNull
	public String getSenha() {return senha;}
	public void setSenha(String senha) {this.senha = senha;}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USUARIO_PERMISSAO", 
		joinColumns = @JoinColumn(name = "USUARIO"), 
		inverseJoinColumns = @JoinColumn(name = "PERMISSAO"))
	public List<Permissao> getPermissoes() {return permissoes;}
	public void setPermissoes(List<Permissao> permissoes) {this.permissoes = permissoes;}
	
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
	