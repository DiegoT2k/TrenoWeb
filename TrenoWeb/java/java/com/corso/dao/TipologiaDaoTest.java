package com.corso.dao;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.corso.base.BaseTest;
import com.corso.config.Beans;
import com.corso.model.Fabbrica;
import com.corso.model.Tipologia;

public class TipologiaDaoTest extends BaseTest{
	
	public static void main(String[] args) {
		testAddTipologia();
	}

	public static void testAddTipologia() {
		   stampa("1-testAddTipologia");
		   
		   BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class);
		   TipologiaDao dao = factory.getBean("tipologiaDao", TipologiaDao.class); 
		   Tipologia tipologia = getTipologia(); 
		   dao.add(tipologia);
		   System.out.println(tipologia);
	}

}
