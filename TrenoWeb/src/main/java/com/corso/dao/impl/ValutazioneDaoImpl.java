package com.corso.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.corso.dao.ValutazioneDao;
import com.corso.model.Treno;
import com.corso.model.Utente;
import com.corso.model.Valutazione;

public class ValutazioneDaoImpl extends DaoImpl implements ValutazioneDao{
	
	@PersistenceContext // figlia di Autowired
	
	private EntityManager manager;  
	
	@Override
	public Valutazione add(Valutazione voto) {
		manager.persist(voto);
		return voto;
	}
	
	public Utente findUtente(int id_utente) {
		Utente u = manager.find(Utente.class, id_utente);
		return u;
	}
	
	public Treno findTreno(int id_treno) {
		Treno t = manager.find(Treno.class, id_treno);
		return t;
	}

}
