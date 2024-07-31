package com.corso.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.corso.model.Fabbrica;
import com.corso.model.Treno;
import com.corso.model.TrenoFilter;
import com.corso.model.Utente;

@Transactional
public interface TrenoDao {
	 
	public int add(Treno treno);
	
	public Utente find(int id);
	
	public Treno findTreno(int id);
	
	public Fabbrica find(String sigla);
	
	public List<Treno> findAll();
	
	public List<Treno> findByFilter(TrenoFilter filter);
	
	public List<Utente> findByName(String nome);
}
