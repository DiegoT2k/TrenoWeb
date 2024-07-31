package com.corso.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.corso.dao.TipologiaDao;
import com.corso.model.Tipologia;

public class TipologiaDaoImpl extends DaoImpl implements TipologiaDao{

	@PersistenceContext // figlia di Autowired
	private EntityManager manager; 
	
	@Override
	public Tipologia add(Tipologia tipologia) {
		//manager.persist mi dà problemi perché ormai detached
		manager.merge(tipologia);
		return tipologia;
	}

	@Override
	public Tipologia find(int id) {
		return manager.find(Tipologia.class, id);
	}
	
}
