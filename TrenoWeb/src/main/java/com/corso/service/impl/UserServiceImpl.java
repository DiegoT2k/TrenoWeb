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
 
	public boolean checkLogin(String username) {
		
        if(utenteDao.findByUsername(username).size() == 0) 
        	return false;
        else
        	return true;
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
//        // Verifica se l'username e' già in uso
//        if (utenteDao.findByUsername(utente.getUsername()).size() > 0) {
//            throw new IllegalArgumentException("Username già in uso");
//        }
//
//        // Verifica se l'email e' già in uso
//        if (utenteDao.findByEmail(utente.getEmail()).size() > 0) {
//            throw new IllegalArgumentException("Email già in uso");
//        }

        // Hash della password usando SHA-256
        //utente.setPassword(DigestUtils.sha256Hex(utente.getPassword()));
      
        // Salva l'utente
        utenteDao.add(utente);
    }	
}
