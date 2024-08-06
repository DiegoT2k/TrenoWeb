package com.corso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.corso.dao.ValutazioneDao;
import com.corso.service.ValutazioneService;

public class ValutazioneServiceImpl implements ValutazioneService{

	@Autowired
	ValutazioneDao valutazioneDao;
	
	@Override
	public void addVoto(int rating, int id_treno, int id_utente) {
		
		valutazioneDao.findVoto(id_treno, id_utente);
		
		valutazioneDao.addVoto(rating, id_treno, id_utente);
		
	}
	
}
