package com.corso.dao;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.corso.base.BaseTest;
import com.corso.config.Beans;
import com.corso.model.Fabbrica;
import com.corso.model.Treno;

public class FabbricaDaoTest extends BaseTest{
	
	public static void main(String[] args) {
		testAddFabbricaDao();
	}
	
	 public static void testAddFabbricaDao() {
		   
		   stampa("3-testAddFabbricaDao");
	   
		   BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class);
		   FabbricaDao dao = factory.getBean("fabbricaDao", FabbricaDao.class); 
		   Fabbrica fabbrica = getFabbrica(); 
		   dao.add(fabbrica);
		   System.out.println(fabbrica);
			   
	  }   
}
