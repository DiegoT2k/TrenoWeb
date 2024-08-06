package com.corso.service;

import java.util.List;

import com.corso.model.Utente;
import com.corso.model.Valutazione;

public interface ValutazioneService {
	
	public void addVoto(int rating, int id_treno, int id_utente);
	
	public List<Valutazione> findByValutazione (Valutazione voto);
	
	public List<Valutazione> getAll();

	public Valutazione findById(int id);
	
	public Utente findUtente(int id);
			
}
