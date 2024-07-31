package com.corso.dao;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.corso.base.BaseTest;
import com.corso.config.Beans;
import com.corso.model.Treno;
import com.corso.model.Valutazione;
import com.corso.model.abs_vagone.Vagone;

public class ValutazioneDaoTest extends BaseTest{

	public static void main(String[] args) {
		
		//testAddValutazione();
		testGetValutazioni();

	}
	
	public static void testAddValutazione() {
		stampa("1- testAddValutazione");
		
		   BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class); 
		   ValutazioneDao daoValutazione = factory.getBean("valutazioneDao", ValutazioneDao.class); 
		   
		   Valutazione valutazione = getVoto(daoValutazione);
		   daoValutazione.add(valutazione);
	}
	
	public static void testGetValutazioni() {
		   BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class); 
		   TrenoDao daoTreno = factory.getBean("trenoDao", TrenoDao.class);
		   
		   Treno treno = daoTreno.findTreno(1);
		   getSetValutazioni(treno);
	}

}
