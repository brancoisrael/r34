package br.com.r34.persistencia.dto.acesso;

import java.io.Serializable;

public class TokenDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8960402971390815947L;

	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = "Bearer "+token;
	}	
	
}
