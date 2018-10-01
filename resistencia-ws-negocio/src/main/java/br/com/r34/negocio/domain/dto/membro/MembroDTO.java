package br.com.r34.negocio.domain.dto.membro;

import br.com.r34.negocio.domain.dto.DataTransferObject;

public class MembroDTO implements DataTransferObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	
	private boolean sucesso;
	
	private String email;
	
	private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

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
