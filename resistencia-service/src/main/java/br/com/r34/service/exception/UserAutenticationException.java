package br.com.r34.service.exception;

public class UserAutenticationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 73890650005905748L;

	public UserAutenticationException() {
		super("Credenciais de acesso inválidas");
	}
}
