package com.corso.exception;

public class SoloCargoException extends TrenoException{

	public SoloCargoException(String sigla, String message) {
		super(sigla, message);
	}

	@Override
	public String getSuggerimento() {
		return "Un treno Cargo non pu� contenere altri tipi di vagone\nInserire " + getSiglaSuggerita()+ " al posto di " + getSigla();
	}

	@Override
	public String getSiglaSuggerita() {
		String s = getSigla().replace("R", "");
		return s.replace("P", "");
	}

	
}
