package com.corso.service.impl;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.corso.config.Beans;
import com.corso.dao.TrenoDao;
import com.corso.dao.VagoneDao;
import com.corso.exception.DimensioneMagException;
import com.corso.exception.DimensioneMinException;
import com.corso.exception.LocomotivaNonInTestaException;
import com.corso.exception.NumLocomotiveException;
import com.corso.exception.NumRistorantiException;
import com.corso.exception.SoloCargoException;
import com.corso.exception.VagoneException;
import com.corso.model.Treno;
import com.corso.model.abs_vagone.Vagone;
import com.corso.model.builder.TrenoBuilder;
import com.corso.model.builder.impl.TrenoItaloBuilder;
import com.corso.model.builder.impl.TrenoTrenordBuilder;

@Component
public class TrenoServiceImpl {
	
	BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class);
	TrenoDao daoTreno = factory.getBean("trenoDao", TrenoDao.class); 
	VagoneDao daoVagone = factory.getBean("vagoneDao", VagoneDao.class); 

	@Autowired
	private TrenoBuilder trenoItaloBuilder;
	
	@Autowired
	private TrenoBuilder trenoTrenordBuilder;
	
	public void creaTreno(String sigla, String fabbrica, int id_utente) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(Beans.class);
		trenoItaloBuilder = (TrenoBuilder) context.getBean(TrenoItaloBuilder.class);
		trenoTrenordBuilder = (TrenoBuilder) context.getBean(TrenoTrenordBuilder.class);

		checkStringa(sigla);
		
		Treno treno = new Treno();
	    treno.setId_utente(daoTreno.find(id_utente));
	    treno.setFabbrica(daoTreno.find(fabbrica));
		
		int id_treno = daoTreno.add(treno);
		System.out.println("id treno = " + id_treno);
		//TrenoBuilder builder = null;
		List<Vagone> lista = null;
		
		if(fabbrica.equals("IT")) {
			//builder = new TrenoItaloBuilder();
			lista = trenoItaloBuilder.costruisciTreno(sigla, id_treno);
		}else if(fabbrica.equals("TN")) {
			//builder = new TrenoTrenordBuilder();
			lista = trenoTrenordBuilder.costruisciTreno(sigla, id_treno);
		}
		
		//List<Vagone> lista = builder.costruisciTreno(sigla, id_treno);
		
		for(Vagone v : lista)
			daoVagone.add(v);
		
	}
	
	public void checkStringa(String vagoni){
		for(char c : vagoni.toCharArray())
			if(!(c == 'H' || c == 'P' || c == 'R' || c == 'C'))
				throw new VagoneException(vagoni, "Errore");			
		
		if(vagoni.charAt(0) != 'H' )
			throw new LocomotivaNonInTestaException(vagoni, "Errore inserimento stringa");	

		if(vagoni.length() <= 1)
			throw new DimensioneMinException(vagoni, "Errore");
			
		if(vagoni.length() >= 20)
			throw new DimensioneMagException(vagoni, "Errore");
		
		if(((String) vagoni.subSequence(1, (vagoni.length())-1)).contains("H") )
			throw new NumLocomotiveException(vagoni, "Errore");
		
		if(vagoni.contains("C") && vagoni.contains("P") ||
				vagoni.contains("C") && vagoni.contains("R")) 
					throw new SoloCargoException(vagoni, "Errore treno cargo");
		
		if(vagoni.indexOf("R") != vagoni.lastIndexOf("R"))
			throw new NumRistorantiException(vagoni, "Errore ristoranti");
				
	}
	
	public List<Treno> allTreni(){
		return daoTreno.findAll();
	}
	
	
}
