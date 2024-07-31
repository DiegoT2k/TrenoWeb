package com.corso.dao;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.corso.base.BaseTest;
import com.corso.config.Beans;
import com.corso.model.Utente;


public class UtenteDaoTest extends BaseTest {

	public static void main(String[] args) {

		testAddUtenteDao();
		//testSet();
		
	}
	
	public static void testSet() {
		   BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class); 
		   TrenoDao daoTreno = factory.getBean("trenoDao", TrenoDao.class); 
		   
		   Utente utente = daoTreno.find(1);
		   getSetTreni(utente); 
	}
	
	 public static void testAddUtenteDao() {
		   
		   stampa("2-testAddUtenteDao");
		   
		   BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class);
		   UtenteDao dao = factory.getBean("utenteDao", UtenteDao.class); 
		   Utente utente = getUtente(); 
		   dao.add(utente);
		   System.out.println(utente);
		   
		   
	  }   
	
	 
}
