package com.example.produtosapi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

import com.example.produtosapi.converter.StringListConverter;

@Embeddable
public class Logradouro {

	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private List<String> telefones = new ArrayList<String>();
	
	@Column( name="ENDERECO" )
	public String getEndereco() {return endereco;}
	public void setEndereco(String endereco) {this.endereco = endereco;}
	
	@Column( name="NUMERO" )
	public String getNumero() {return numero;}
	public void setNumero(String numero) {this.numero = numero;}
	
	@Column( name="COMPLEMENTO" )
	public String getComplemento() {return complemento;}
	public void setComplemento(String complemento) {this.complemento = complemento;}
	
	@Column( name="BAIRRO" )
	public String getBairro() {return bairro;}
	public void setBairro(String bairro) {this.bairro = bairro;}
	
	@Column( name="CEP" )
	public String getCep() {return cep;}
	public void setCep(String cep) {this.cep = cep;}
	
	@Column( name="CIDADE" )
	public String getCidade() {return cidade;}
	public void setCidade(String cidade) {this.cidade = cidade;}
	
	@Column( name="ESTADO" )
	public String getEstado() {return estado;}
	public void setEstado(String estado) {this.estado = estado;}
	
	@Column( name="TELEFONES" )
	@Convert(converter = StringListConverter.class)
	public List<String> getTelefones() {return telefones;}
	public void setTelefones(List<String> telefones) {this.telefones = telefones;}
	
}
