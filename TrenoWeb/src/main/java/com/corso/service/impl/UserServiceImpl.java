package com.corso.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import javax.validation.Valid;

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
 
	@Override
	public Utente checkLogin(String username) {
   		List<Utente> utenti = utenteDao.findByUsername(username);

   		if (utenti != null && utenti.size() > 0) {
   			return utenti.get(0);
   		}
   		return null;

   	}
	
	@Override
    public boolean isUsernameUnique(String username) {
        return utenteDao.findByUsername(username).isEmpty();
    }

    @Override
    public boolean isEmailUnique(String email) {
        return utenteDao.findByEmail(email).isEmpty();
    }
    
	@Override
	public void save(@Valid Utente utente) {
		System.out.println("entro in save");

        utenteDao.add(utente);
    }	
	
	@Override
    public Utente findById(int id) {
        return utenteDao.findById(id);
    }
}
