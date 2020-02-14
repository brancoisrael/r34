package br.com.r34.persistencia.enums;

public enum Patente {
	RODANDO("Rodando"),PP("PP"),MEIO_ESCUDO("Meio Escudo"),ESCUDADO("Escudado");
	
	private String descricao;
	
	private Patente(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
