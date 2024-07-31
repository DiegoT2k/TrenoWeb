package com.corso.exception;

public abstract class TrenoException extends RuntimeException{
	
	private String sigla;
	
	public TrenoException(String sigla, String message){
		super(message);
		this.sigla = sigla;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public abstract String getSuggerimento();
	public abstract String getSiglaSuggerita();

}
