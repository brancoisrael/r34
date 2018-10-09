package br.com.r34.negocio.domain.dto.lancamento;

import br.com.r34.negocio.domain.dto.DataTransferObject;

public class DescricaoLancamentoDTO implements DataTransferObject {

	private static final long serialVersionUID = 901146937414941638L;

	private String message;

	private boolean sucesso;

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

}
