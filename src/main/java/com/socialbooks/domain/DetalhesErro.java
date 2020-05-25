package com.socialbooks.domain;

public class DetalhesErro {
	
	private String titulo;
	private Long status;
	private Long timesramp;
	private String mensagemDesenvolvedor;
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Long getStatus() {
		return status;
	}
	
	public void setStatus(Long status) {
		this.status = status;
	}
	
	public Long getTimesramp() {
		return timesramp;
	}
	
	public void setTimesramp(Long timesramp) {
		this.timesramp = timesramp;
	}
	
	public String getMensagemDesenvolvedor() {
		return mensagemDesenvolvedor;
	}
	
	public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
	}
}
