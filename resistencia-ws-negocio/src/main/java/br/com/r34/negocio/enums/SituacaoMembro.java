package br.com.r34.negocio.enums;

public enum SituacaoMembro {
	
	ATIVO("Ativo"),LICENCA("Em licencao"),DESLIGADO("Desligado");
	
	private String descricao;
	
	private SituacaoMembro(String descricao) {
		this.descricao = descricao; 
	}
	
	public String getDescricao() {
		return descricao;
	}
}
