package com.corso.dao;

import java.util.List;

import javax.transaction.Transactional;
import com.corso.model.Treno;
import com.corso.model.Utente;
import com.corso.model.Valutazione;

@Transactional
public interface ValutazioneDao {

	public Valutazione add(Valutazione voto);
	
	public Utente findUtente(int id);
	
	public Treno findTreno(int id);
	
	public void addVoto(int rating, int id_treno, int id_utente);
	
	public void findVoto(int id_treno, int id_utente);
	
	public List<Valutazione> findByValutazione (Valutazione voto);
	
	public List<Valutazione> getAll();
	
	public Valutazione findById(int id);
	
}
