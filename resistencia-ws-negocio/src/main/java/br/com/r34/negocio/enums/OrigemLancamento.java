package br.com.r34.negocio.enums;

public enum OrigemLancamento {

	BAR("Bar"), CAIXINHA("Caixinha"), MULTA_WHATSAPP("Multa Whatsapp"), MULTA_ATRASO_CAIXINHA("Mulda de atraso de pagamento de caixinha"), AQUISICAO_BENS("Aquisição de bens"), AQUISICAO_SERVICO("Aquisição de serviços"); 
	
	private String descricao;
	
	private OrigemLancamento(String descricao) {
		this.descricao = descricao; 
	}
	
	public String getDescricao() {
		return descricao;
	}
}
