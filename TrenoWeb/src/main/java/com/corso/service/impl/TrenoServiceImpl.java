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
import com.corso.dao.ValutazioneDao;
import com.corso.dto.TrenoCompleto;
import com.corso.dto.TrenoVoto;
import com.corso.exception.DimensioneMagException;
import com.corso.exception.DimensioneMinException;
import com.corso.exception.LocomotivaNonInTestaException;
import com.corso.exception.NumLocomotiveException;
import com.corso.exception.NumRistorantiException;
import com.corso.exception.SoloCargoException;
import com.corso.exception.VagoneException;
import com.corso.model.Treno;
import com.corso.model.Valutazione;
import com.corso.model.abs_vagone.Vagone;
import com.corso.model.builder.TrenoBuilder;
import com.corso.model.builder.impl.TrenoItaloBuilder;
import com.corso.model.builder.impl.TrenoTrenordBuilder;
import com.corso.service.TrenoService;

public class TrenoServiceImpl implements TrenoService{

	@Autowired
	TrenoDao trenoDao;
	
	@Autowired
	VagoneDao vagoneDao;
	
	@Autowired
	private TrenoBuilder trenoItaloBuilder;
	
	@Autowired
	private TrenoBuilder trenoTrenordBuilder;
	
	@Override
	public void creaTreno(String sigla, String fabbrica, int id_utente) {

		checkStringa(sigla);
		
		Treno treno = new Treno();
	    treno.setId_utente(trenoDao.find(id_utente));
	    treno.setFabbrica(trenoDao.find(fabbrica));
		
		int id_treno = trenoDao.add(treno);
		System.out.println("id treno = " + id_treno);
		
		List<Vagone> lista = null;
		
		if(fabbrica.equals("IT")) {
			lista = trenoItaloBuilder.costruisciTreno(sigla, id_treno);
		}else if(fabbrica.equals("TN")) {
			lista = trenoTrenordBuilder.costruisciTreno(sigla, id_treno);
		}
		
		for(Vagone v : lista)
			vagoneDao.add(v);
		
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
	
	@Override
	public List<Treno> allTreni(){
		return trenoDao.findAll();
	}
	
	@Override
	public List<TrenoVoto> votoTreni(){
		List<TrenoVoto> all = trenoDao.findTrenoVoto();
		
		return all;
	}
	
	@Override
	public List<TrenoCompleto> trenoCompleto(){
		List<TrenoCompleto> all = trenoDao.findTrenoCompleto();
		
		return all;
	}
	
	@Override
    public List<TrenoCompleto> findByIdUtente(int utenteId) {
        return trenoDao.findByIdUtente(utenteId);
    }
}
