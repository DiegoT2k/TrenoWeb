package com.corso.service.impl;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.corso.config.Beans;
import com.corso.dao.UtenteDao;

public class UserServiceImpl {

	BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class);
	UtenteDao dao = factory.getBean("utenteDao", UtenteDao.class); 
	
	public boolean checkLogin(String username) {
		
        if(dao.findByUsername(username).size() == 0) 
        	return false;
        else
        	return true;
	}
	
}
