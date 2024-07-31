package com.corso.dao;

import javax.transaction.Transactional;

import com.corso.model.Treno;
import com.corso.model.Utente;
import com.corso.model.Valutazione;

@Transactional
public interface ValutazioneDao {

	public Valutazione add(Valutazione voto);
	public Utente findUtente(int id);
	public Treno findTreno(int id);
}
