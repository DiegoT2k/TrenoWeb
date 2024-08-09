package com.corso.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.corso.model.Utente;
	
@Transactional
public interface UtenteDao {
	
	public Utente add(Utente utente);
	
	public List<Utente> findByUsername(String username);
	
	public List<Utente> findByEmail(String email);
	
	public Utente findById(int id);
	
}

