package com.corso.exception;

public class VagoneException extends TrenoException{

	public VagoneException(String sigla, String message) {
		super(sigla, message);
	}

	@Override
	public String getSuggerimento() {
		return "La stringa inserita " + getSigla() + " ha dei caratteri non consoni alla costruzione di un treno";
	}

	@Override
	public String getSiglaSuggerita() {
		return null;
	}

}
