package br.com.r34.persistencia.dto.acesso;

import java.io.Serializable;

public class LoginDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5300703275576701611L;
	
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
	
	
}
