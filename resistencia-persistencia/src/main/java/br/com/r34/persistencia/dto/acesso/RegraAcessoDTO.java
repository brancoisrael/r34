package br.com.r34.persistencia.dto.acesso;

import br.com.r34.persistencia.dto.DataTransferObject;

public class RegraAcessoDTO implements DataTransferObject{

	private static final long serialVersionUID = 6361002703106664390L;

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
