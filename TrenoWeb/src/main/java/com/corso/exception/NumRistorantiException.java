package com.corso.exception;

public class NumRistorantiException extends TrenoException{

	public NumRistorantiException(String sigla, String message) {
		super(sigla, message);
	}

	@Override
	public String getSuggerimento() {
		return "La stringa " + getSigla() + " contiene troppi ristoranti\nInserire " + getSiglaSuggerita();
	}

	@Override
	public String getSiglaSuggerita() {
		return getSigla().replaceAll("R", "") + "R";
	}

}
