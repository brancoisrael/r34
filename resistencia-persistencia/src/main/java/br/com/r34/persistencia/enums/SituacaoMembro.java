package br.com.r34.persistencia.enums;

public enum SituacaoMembro {
	
	ATIVO("Ativo"),LICENCA("Em licencao"),DESLIGADO("Desligado"), INATIVO ("Inativo");
	
	private String descricao;
	
	private SituacaoMembro(String descricao) {
		this.descricao = descricao; 
	}
	
	public String getDescricao() {
		return descricao;
	}
}
