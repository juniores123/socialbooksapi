package com.socialbooks.services.exceptions;

public class AutorNãoEncontradoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public AutorNãoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public AutorNãoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
