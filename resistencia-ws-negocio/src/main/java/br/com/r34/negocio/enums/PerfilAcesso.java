package br.com.r34.negocio.enums;

public enum PerfilAcesso {

	DESENVOLVEDOR("Desenvolvedor"),TESOURARIA("Tesouraria"),SECRETARIA("Secretaria")
	,CAPITANIA("Capitania"), ESCUDADO("Escudado"), NAO_ESCUDADO("Nao escudado");
	
	private String descricao;
	
	private PerfilAcesso(String descricao) {
		this.descricao = descricao; 
	}
	
	public String getDescricao() {
		return descricao;
	}
}
