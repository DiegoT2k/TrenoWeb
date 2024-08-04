package com.corso.service;

import com.corso.model.Utente;

public interface UserService {
	
	public boolean checkLogin(String username);
	
	public void save(Utente utente);
	
	boolean isUsernameUnique(String username);
	
    boolean isEmailUnique(String email);
	
}
