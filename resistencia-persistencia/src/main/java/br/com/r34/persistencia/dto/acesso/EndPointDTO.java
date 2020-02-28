package br.com.r34.persistencia.dto.acesso;

import java.io.Serializable;

public class EndPointDTO implements Serializable{

	private static final long serialVersionUID = 6882828183145771188L;

	private String message;
	
	private boolean sucesso;

	private String url;
	
	private boolean menuLateral;

	public EndPointDTO() {}
	

	public EndPointDTO(String url) {
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isMenuLateral() {
		return menuLateral;
	}

	public void setMenuLateral(boolean menuLateral) {
		this.menuLateral = menuLateral;
	}
	
	
}
