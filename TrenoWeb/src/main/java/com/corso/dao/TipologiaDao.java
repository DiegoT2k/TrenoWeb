package com.corso.dao;

import javax.transaction.Transactional;

import com.corso.model.Tipologia;


@Transactional
public interface TipologiaDao {

	public Tipologia add(Tipologia tipologia);
	
	public Tipologia find(int id);
	
}
