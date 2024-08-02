package com.corso.service;

import com.corso.model.Utente;

public interface UserService {
	
	public void save(Utente utente);
	
	public void validatePassword(String password);

	public Utente checkLogin(String username);
	
}
