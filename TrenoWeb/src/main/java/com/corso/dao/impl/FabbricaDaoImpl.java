package com.corso.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.corso.dao.FabbricaDao;
import com.corso.model.Fabbrica;

public class FabbricaDaoImpl implements FabbricaDao{
	
	@PersistenceContext // figlia di Autowired
	
	private EntityManager manager; 
	
	@Override
	public Fabbrica add(Fabbrica fabbrica) {
		manager.persist(fabbrica);
		return fabbrica;
	}

}
