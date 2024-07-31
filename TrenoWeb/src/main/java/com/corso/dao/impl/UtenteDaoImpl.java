package com.corso.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.corso.dao.UtenteDao;
import com.corso.model.Utente;

public class UtenteDaoImpl extends DaoImpl implements UtenteDao{

	@PersistenceContext // figlia di Autowired
	
	private EntityManager manager;  
	
	@Override
	public Utente add(Utente utente) {
		manager.persist(utente);
		return utente;
	}

}


