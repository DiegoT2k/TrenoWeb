package com.corso.service;

import com.corso.model.Utente;

public interface UserService {
	
	public void save(Utente utente);
	
	boolean isUsernameUnique(String username);
	
  boolean isEmailUnique(String email);

	public Utente checkLogin(String username);

	
}
