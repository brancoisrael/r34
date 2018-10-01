package br.com.r34.negocio.domain.dto;

import java.io.Serializable;

public interface DataTransferObject extends Serializable {

	String getMessage();
	
	void setMessage(String message);
	
	boolean isSucesso();
	
	void setSucesso(boolean sucesso);
	
}
