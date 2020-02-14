package br.com.r34.persistencia.enums;

public enum TipoLancamento {

	DEBITO("Débito"), CREDITO("Crédito");
	
	private String descricao;
	
	private TipoLancamento(String descricao) {
		this.descricao = descricao; 
	}
	
	public String getDescricao() {
		return descricao;
	}
}
