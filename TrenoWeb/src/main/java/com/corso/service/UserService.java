package com.corso.service;

import com.corso.model.Utente;

public interface UserService {
	
	public boolean checkUsername(String username);
	
	public boolean checkPassword(String username, String password);
	
	public void save(Utente utente);
	
	public void validatePassword(String password);
	
}
