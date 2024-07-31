package com.corso.dao;

import javax.transaction.Transactional;

import com.corso.model.Fabbrica;
import com.corso.model.Tipologia;
import com.corso.model.Treno;
import com.corso.model.abs_vagone.Vagone;

@Transactional
public interface VagoneDao {

	public Vagone add(Vagone vagone);
	
	public Treno find(int id);
	
	public Fabbrica find(String fabbrica);
	
	public Tipologia findTipo(String tipo);
}
