package com.corso.exception;

public class LocomotivaNonInTestaException extends TrenoException{

	public LocomotivaNonInTestaException(String sigla, String message) {
		super(sigla, message);
	}

	@Override
	public String getSuggerimento() {
		return "Inserire la sigla " + getSiglaSuggerita() + " invece di " + getSigla() + "\n";
	}

	@Override
	public String getSiglaSuggerita() {
		return "H" + getSigla();
	}

}
