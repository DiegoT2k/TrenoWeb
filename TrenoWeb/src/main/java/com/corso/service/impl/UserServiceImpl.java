package com.corso.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.corso.config.Beans;
import com.corso.dao.UtenteDao;
import com.corso.model.Utente;
import com.corso.service.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	UtenteDao utenteDao;
	
	private static final int MIN_PASSWORD_LENGTH = 8;
	
	//Verifica se la stringa contiene almeno una lettera maiuscola
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile(".*[A-Z].*");
    
    //Verifica se la stringa contiene almeno una lettera minuscola
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile(".*[a-z].*");
    
    //Verifica se la stringa contiene almeno una cifra numerica
    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*\\d.*");
    
    // Verifica se la stringa contiene almeno un carattere speciale
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(".*[^a-zA-Z0-9].*");
	
    

    @Override
   	public Utente checkLogin(String username) {
   		List<Utente> utenti = utenteDao.findByUsername(username);
   		
   		if (utenti != null && utenti.size() > 0) {
   			return utenti.get(0);
   		}
   		return null;
      
   	}
 
	
	public void save(Utente utente) {
		System.out.println("entro in save");
        // Verifica se l'username e' gia'� in uso
        if (utenteDao.findByUsername(utente.getUsername()).size() > 0) {
            throw new IllegalArgumentException("Username già in uso");
        }

        // Verifica se l'email e' gia' in uso
        if (utenteDao.findByEmail(utente.getEmail()).size() > 0) {
            throw new IllegalArgumentException("Email già in uso");
        }
               
        validatePassword(utente.getPassword());

        // Hash della password usando SHA-256
        //utente.setPassword(DigestUtils.sha256Hex(utente.getPassword()));
      
        // Salva l'utente
        utenteDao.add(utente);
    }
	
	public void validatePassword(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("La password deve essere lunga almeno " + MIN_PASSWORD_LENGTH + " caratteri");
        }
        if (!UPPERCASE_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException("La password deve contenere almeno una lettera maiuscola");
        }
        if (!LOWERCASE_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException("La password deve contenere almeno una lettera minuscola");
        }
        if (!DIGIT_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException("La password deve contenere almeno un numero");
        }
        if (!SPECIAL_CHAR_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException("La password deve contenere almeno un carattere speciale");
        }
    }


	
}
