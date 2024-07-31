package com.corso.exception;

public class DimensioneMagException extends TrenoException{

	public DimensioneMagException(String sigla, String message) {
		super(sigla, message);
	}

	@Override
	public String getSuggerimento() {
		return "Stringa " + getSigla() + " troppo lunga, " + getSiglaSuggerita();
	}

	@Override
	public String getSiglaSuggerita() {
		return "inserire meno vagoni";
	}

}
