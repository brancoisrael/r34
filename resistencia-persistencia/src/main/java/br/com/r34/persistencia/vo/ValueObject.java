package br.com.r34.persistencia.vo;

import java.io.Serializable;

public interface ValueObject extends Serializable{

	public void setId(long id);
	
	public long getId();
}
