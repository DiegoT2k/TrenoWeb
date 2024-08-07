package com.corso.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

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
import com.corso.model.Utente;
import com.corso.model.Valutazione;
import com.corso.model.abs_vagone.Vagone;
import com.corso.model.builder.TrenoBuilder;
import com.corso.model.builder.impl.TrenoItaloBuilder;
import com.corso.model.builder.impl.TrenoTrenordBuilder;
import com.corso.model.vagone.CargoAbs;
import com.corso.model.vagone.LocomotivaAbs;
import com.corso.model.vagone.PasseggeriAbs;
import com.corso.model.vagone.RistoranteAbs;
import com.corso.service.TrenoService;
import com.corso.service.UserService;
import com.corso.vo.FiltroVO;

@Transactional
public class TrenoServiceImpl implements TrenoService{

	@Autowired
	TrenoDao trenoDao;
	
	@Autowired
	VagoneDao vagoneDao;
	
	@Autowired
	private UserService userServiceImpl;
	
	@Autowired
	private TrenoBuilder trenoItaloBuilder;
	
	@Autowired
	private TrenoBuilder trenoTrenordBuilder;
	
	//@Autowired
	//private Treno treno;
	
	@Override
	public void creaTreno(String sigla, String fabbrica, int id_utente) {

		checkStringa(sigla);
		Treno treno = new Treno();
	    treno.setId_utente(trenoDao.find(id_utente));
	    treno.setFabbrica(trenoDao.find(fabbrica));
	    treno.setSigla(sigla);
		
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
	public List<TrenoCompleto> trenoCompleto(){
		List<TrenoCompleto> all = trenoDao.findTrenoCompleto();
		
		return all;
	}
	
	@Override
	public List<TrenoCompleto> filtraTreno(FiltroVO filtroVO){
		
		Utente utente = userServiceImpl.find(filtroVO.getUsername());
		System.out.println(utente);
		List<TrenoCompleto> all = trenoDao.filtraTrenoCompleto(filtroVO.getPrezzoMin(), filtroVO.getPrezzoMax(), 
				filtroVO.getLunghezzaMin(), filtroVO.getLunghezzaMax(), filtroVO.getPesoMin(), 
				filtroVO.getPesoMax(), filtroVO.getSigla(), utente);
		
		return all;
	}
	
	@Override
    public List<TrenoCompleto> findByIdUtente(int utenteId) {
        return trenoDao.findByIdUtente(utenteId);
    }

	@Override
	public Treno findTreno(int idTreno) {
		return trenoDao.findTreno(idTreno);
	}

	@Override
	public void updateTreno(Treno treno) {
		trenoDao.updateTreno(treno);
	}
	
	@Override
    public void deleteTreno(int idTreno) {
        trenoDao.deleteTreno(idTreno);
    }
	
	@Override
	@Transactional
	public void updateTrenoSigla(Treno treno, String newSigla) {
        checkStringa(newSigla);

        treno.setSigla(newSigla);
        
        trenoDao.updateTreno(treno);
        
        deleteVagoniByTreno(treno);
        
        recreateVagoni(treno);
    }
	
	@Override
	@Transactional
	public void deleteVagoniByTreno(Treno treno) {
        trenoDao.deleteVagoniByTreno(treno);
    }

	@Override
    public void recreateVagoni(Treno treno) {
		String sigla = treno.getSigla();
		System.out.println("sigla nuova: " + sigla);
	    String fabbrica = treno.getFabbrica().getSigla();
	    int id_treno = treno.getId_treno();
	    
	    // Costruisci una nuova lista di vagoni
	    List<Vagone> lista = null;
	    if (fabbrica.equals("IT")) {
	        lista = trenoItaloBuilder.costruisciTreno(sigla, id_treno);
	    } else if (fabbrica.equals("TN")) {
	        lista = trenoTrenordBuilder.costruisciTreno(sigla, id_treno);
	    }
	    
	    // Aggiungi i nuovi vagoni
	    for (Vagone v : lista) {
	        vagoneDao.add(v);
	    }
    }
	
	@Override
	public TrenoCompleto findTrenoCompletoById(int idTreno) {
	    return trenoDao.findTrenoCompletoById(idTreno);
	}
	
	@Override
    public Treno duplicateTreno(String sigla) {
		// Recupera i dati del treno esistente
        TrenoCompleto trenoCompleto = trenoDao.findTrenoCompletoBySigla(sigla);
        if (trenoCompleto == null) {
            throw new IllegalArgumentException("Treno non trovato con la sigla: " + sigla);
        }

        // Crea un nuovo treno con le stesse proprietà
        Treno nuovoTreno = new Treno();
        nuovoTreno.setSigla(trenoCompleto.getSigla());
        nuovoTreno.setFabbrica(trenoCompleto.getFabbrica());
        nuovoTreno.setId_utente(trenoCompleto.getId_utente());

     // Salva il nuovo treno e ottieni il suo ID
        int idNuovoTreno;
        try {
            idNuovoTreno = trenoDao.add(nuovoTreno);
            System.out.println("Nuovo treno creato con ID: " + idNuovoTreno);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        // Costruisci i vagoni per il nuovo treno
        List<Vagone> listaVagoni = null;

        if (trenoCompleto.getFabbrica().equals("IT")) {
            listaVagoni = trenoItaloBuilder.costruisciTreno(nuovoTreno.getSigla(), idNuovoTreno);
        } else if (trenoCompleto.getFabbrica().equals("TN")) {
            listaVagoni = trenoTrenordBuilder.costruisciTreno(nuovoTreno.getSigla(), idNuovoTreno);
        }
        
        System.out.println("Numero di vagoni da salvare: " + listaVagoni.size());
        
        // Aggiungi i nuovi vagoni al database e associa al nuovo treno
        for (Vagone vagone : listaVagoni) {
            vagone.setId_treno(nuovoTreno); // Associa il vagone al nuovo treno
            System.out.println("Salvando vagone: " + vagone);
            vagoneDao.add(vagone);
        }

        return nuovoTreno;
    }
}
