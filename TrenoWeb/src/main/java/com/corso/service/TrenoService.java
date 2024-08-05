package com.corso.service;

import java.util.List;

import com.corso.dto.TrenoCompleto;
import com.corso.dto.TrenoVoto;
import com.corso.model.Treno;

public interface TrenoService {

	public void creaTreno(String sigla, String fabbrica, int id_utente);
	
	public void checkStringa(String vagoni);
	
	public List<Treno> allTreni();
	
	public List<TrenoVoto> votoTreni();

	public List<TrenoCompleto> trenoCompleto();
}
