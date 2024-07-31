package com.corso.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.corso.dao.TrenoBuilderDao;

public class TrenoBuilderDaoImpl extends DaoImpl implements TrenoBuilderDao{
	
	@PersistenceContext // figlia di Autowired
	
	private EntityManager manager; 
	
	@Override
	public void creaTreno() {
		
	}

}
