package com.corso.exception;

public class DimensioneMinException extends TrenoException{

	public DimensioneMinException(String sigla, String message) {
		super(sigla, message);
	}

	@Override
	public String getSuggerimento() {
		return "Stringa " + getSigla() + " troppo corta, " + getSiglaSuggerita();
	}

	@Override
	public String getSiglaSuggerita() {
		return "inserire almeno un vagone oltre alla locomotiva";
	}

}
