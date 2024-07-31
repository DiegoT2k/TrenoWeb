package com.corso.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.corso.dao.VagoneDao;
import com.corso.model.Fabbrica;
import com.corso.model.Tipologia;
import com.corso.model.Treno;
import com.corso.model.Utente;
import com.corso.model.abs_vagone.Vagone;

public class VagoneDaoImpl extends DaoImpl implements VagoneDao{

	@PersistenceContext // figlia di Autowired
	
	private EntityManager manager;  
	
	@Override
	public Vagone add(Vagone vagone) {
		manager.persist(vagone);
		return vagone;
	}

	@Override
	public Treno find(int id) {
		return (Treno) super.find(Treno.class, id);
	}

	@Override
	public Fabbrica find(String sigla) {
		return manager.find(Fabbrica.class, sigla);
	}
	
	@Override
	public Tipologia findTipo(String tipo) {
		return manager.find(Tipologia.class, tipo);
	}
	
	
}
