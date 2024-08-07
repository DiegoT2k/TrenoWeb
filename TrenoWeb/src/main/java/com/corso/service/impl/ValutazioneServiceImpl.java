package com.corso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.corso.dao.ValutazioneDao;
import com.corso.model.Utente;
import com.corso.model.Valutazione;
import com.corso.service.ValutazioneService;

public class ValutazioneServiceImpl implements ValutazioneService{

	@Autowired
	ValutazioneDao valutazioneDao;
	
	@Override
	public void addVoto(int rating, int id_treno, int id_utente) {
		
		valutazioneDao.findVoto(id_treno, id_utente);
		
		valutazioneDao.addVoto(rating, id_treno, id_utente);
		
	}

	@Override
	public List<Valutazione> findByValutazione(Valutazione voto) {
		
		return valutazioneDao.findByValutazione(voto);

	}

	@Override
	public List<Valutazione> getAll() {
		
		return valutazioneDao.getAll();
	}

	@Override
	public Valutazione findById(int id) {
		return valutazioneDao.findById(id);
	}

	@Override
	public Utente findUtente(int id) {
		return valutazioneDao.findUtente(id);

	}
	
	
	
}
