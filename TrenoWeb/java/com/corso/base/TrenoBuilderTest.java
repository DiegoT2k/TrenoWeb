package com.corso.base;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.corso.config.Beans;
import com.corso.dao.FabbricaDao;
import com.corso.dao.VagoneDao;
import com.corso.model.Fabbrica;
import com.corso.model.abs_vagone.Vagone;
import com.corso.model.builder.TrenoBuilder;
import com.corso.model.builder.impl.TrenoItaloBuilder;
import com.corso.service.impl.TrenoServiceImpl;

public class TrenoBuilderTest extends BaseTest{

	public static void main(String[] args) {
		
		//testCreaTreno();
		testCreaTrenoService();
	}
	
	public static void testCreaTreno() {
		
		   stampa("1- testCreaTreno");
		   
		   BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class);
		   TrenoItaloBuilder builder = factory.getBean("trenoItaloBuilder", TrenoItaloBuilder.class);  
		   List<Vagone> l = builder.costruisciTreno("HH", 3);
		   
		   
		   
		   VagoneDao dao = factory.getBean("vagoneDao", VagoneDao.class);
		   for(Vagone v : l) {
			   dao.add(v);
		   }
	}
	
	public static void testCreaTrenoService() {
		
		TrenoServiceImpl trenoService = new TrenoServiceImpl();
		
		trenoService.creaTreno("HH", "IT", 22, 1);
		
	}
	
}
