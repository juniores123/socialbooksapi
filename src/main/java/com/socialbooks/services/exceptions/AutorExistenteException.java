package com.socialbooks.services.exceptions;

public class AutorExistenteException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public AutorExistenteException(String mensagem) {
		super(mensagem);
	}
	
	public AutorExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
