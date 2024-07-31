package com.corso.exception;

public class NumLocomotiveException extends TrenoException{

	public NumLocomotiveException(String sigla, String message) {
		super(sigla, message);
	}

	@Override
	public String getSuggerimento() {
		return "Locomotive all'interno del treno, inserire " + getSiglaSuggerita() + " al posto di " + getSigla();
	}

	@Override
	public String getSiglaSuggerita() {
		return getSigla().charAt(0) + ((String) getSigla().subSequence(1, (getSigla().length())-1)).replaceAll("H", "") + getSigla().charAt(getSigla().length()-1);
	}

}
