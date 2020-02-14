package br.com.r34.persistencia.enums;

public enum Cargo {

	PRESIDENTE("Presidente"),VICE_PRESIDENTE("Vice Presidente"),SARGENTO_ARMAS("Sargento de armas"),SECRETARIO("Secretário"),TESOUREIRO("Tesoureiro"), SEM_CARGO("Sem cargo");
	
	private String descricao;
	
	private Cargo(String descricao) {
		this.descricao = descricao; 
	}
	
	public String getDescricao() {
		return descricao;
	}
}
