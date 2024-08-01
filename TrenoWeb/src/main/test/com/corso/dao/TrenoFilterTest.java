package com.corso.dao;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.corso.base.BaseTest;
import com.corso.config.Beans;
import com.corso.model.TrenoFilter;

public class TrenoFilterTest extends BaseTest{

	public static void main(String[] args) {
		BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class);
		TrenoDao dao = factory.getBean("trenoDao", TrenoDao.class);
		
		//TrenoFilter trenoFilter = new TrenoFilter();
		
		testFilter(dao);
	}

	public static void testFilter(TrenoDao dao) {
		
		stampa("1- testFiltro per il peso");
		List<Object[]> l = dao.filterByPeso();
		
		for(int i = 0; i < l.size(); i++) {
			System.out.println("id_treno = " + l.get(i)[0] + " peso = " + l.get(i)[1]);
		}
		
		
	}
	
}
