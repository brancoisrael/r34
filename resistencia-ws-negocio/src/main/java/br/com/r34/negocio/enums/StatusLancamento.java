package br.com.r34.negocio.enums;

public enum StatusLancamento {

	AGUARDANDO_QUITACAO("Aguardando quitacao"), QUITADO("Quitado"), EM_ATRASO("Em atraso");
	
	private String descricao;
	
	private StatusLancamento(String descricao) {
		this.descricao = descricao; 
	}
	
	public String getDescricao() {
		return descricao;
	}
}
