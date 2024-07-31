package com.corso.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.corso.base.BaseTest;
import com.corso.config.Beans;
import com.corso.model.Treno;
import com.corso.model.TrenoFilter;

public class TrenoDaoTest extends BaseTest{

	public static void main(String[] args) {
		
	   BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class);
	   TrenoDao dao = factory.getBean("trenoDao", TrenoDao.class);
	   //testAddTrenoDao(dao);
	   testFiltro(dao);
	}
	
	 public static void testAddTrenoDao(TrenoDao dao) {
		   
		   stampa("1-testAddTrenoDao");
		   Treno treno = getTreno(dao); 
		   dao.add(treno);
		   System.out.println(treno);
		   
	  }   
	
	 @Transactional
	 public static void testFiltro(TrenoDao dao) {
		 stampa("2- testFiltro");
		 TrenoFilter filter = new TrenoFilter();
//		 filter.setBigliettiMin(null);
//		 filter.setBigliettiMax(50);
//		 
		 //Non trova prezzo, lunghezza e peso perché sono Attributi in vagone
		 filter.setPrezzoMin(null);
		 filter.setPrezzoMax(null);
		 
		 filter.setLunghezzaMin(null);
		 filter.setLunghezzaMax(null);
		 
		 filter.setPesoMin(210.0);
		 filter.setPesoMax(null);
		 
		 List<Treno> lista = dao.findByFilter(filter);
		
		 System.out.println(lista);
	 }
}
