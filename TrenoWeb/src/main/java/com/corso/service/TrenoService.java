package com.corso.service;

import java.util.List;

import com.corso.model.Treno;

public interface TrenoService {

	public void creaTreno(String sigla, String fabbrica, int id_utente);
	
	public void checkStringa(String vagoni);
	
	public List<Treno> allTreni();

}
