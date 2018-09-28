package br.com.r34.negocio.domain.vo;

import java.io.Serializable;

public interface ValueObject extends Serializable{

	public void setId(long id);
	
	public long getId();
}
