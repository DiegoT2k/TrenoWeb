package com.corso.dao;

import javax.transaction.Transactional;

import com.corso.model.Fabbrica;

@Transactional
public interface FabbricaDao {
	 
	public Fabbrica add(Fabbrica fabbrica);
	
}
