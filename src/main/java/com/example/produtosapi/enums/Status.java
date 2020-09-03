package com.example.produtosapi.enums;

public enum Status {

	ATIVO("Ativo"), 
	INATIVO("Inativo"), 
	DELETADO("Deletado");
	
	String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
