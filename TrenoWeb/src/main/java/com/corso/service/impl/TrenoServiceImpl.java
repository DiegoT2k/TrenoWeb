package com.corso.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.corso.dao.TrenoDao;
import com.corso.dao.VagoneDao;
import com.corso.dto.FiltroDTO;
import com.corso.dto.TrenoCompletoDTO;
import com.corso.exception.*;
import com.corso.model.Treno;
import com.corso.model.Utente;
import com.corso.model.abs_vagone.Vagone;
import com.corso.model.builder.TrenoBuilder;
import com.corso.service.TrenoService;
import com.corso.service.UserService;
import com.corso.vo.FiltroVO;

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
			lista = trenoItaloBuilder.costruisciTreno(sigla);
			
			for(Vagone v : lista) {
			    v.setId_treno(vagoneDao.find(id_treno));
			    v.setFabbrica(vagoneDao.find("IT"));	
			}
			
		}else if(fabbrica.equals("TN")) {
			lista = trenoTrenordBuilder.costruisciTreno(sigla);
			
			for(Vagone v : lista) {
			    v.setId_treno(vagoneDao.find(id_treno));
			    v.setFabbrica(vagoneDao.find("TN"));	
			}
			
		}
		
		for(Vagone v : lista) {
			vagoneDao.add(v);
		}
		
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
	public List<TrenoCompletoDTO> trenoCompleto(){
		List<TrenoCompletoDTO> all = trenoDao.findTrenoCompleto();
		
		return all;
	}
	
	@Override
	public List<TrenoCompletoDTO> filtraTreno(FiltroVO filtroVO){
		FiltroDTO filtroDTO = new FiltroDTO();
		BeanUtils.copyProperties(filtroVO, filtroDTO);
		Utente utente = userServiceImpl.find(filtroDTO.getUsername());
	
		List<TrenoCompletoDTO> all = trenoDao.filtraTrenoCompleto(filtroDTO, utente);
		
		return all;
	}
	
	@Override
    public List<TrenoCompletoDTO> findByIdUtente(int utenteId) {
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
	public void updateTrenoSigla(Treno treno, String newSigla) {
        checkStringa(newSigla);
        treno.setSigla(newSigla);
        trenoDao.updateTreno(treno);     
        deleteVagoniByTreno(treno);    
        recreateVagoni(treno);
    }
	
	@Override
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
		
		if(fabbrica.equals("IT")) {
			lista = trenoItaloBuilder.costruisciTreno(sigla);
			
			for(Vagone v : lista) {
			    v.setId_treno(vagoneDao.find(id_treno));
			    v.setFabbrica(vagoneDao.find("IT"));	
			}
			
		}else if(fabbrica.equals("TN")) {
			lista = trenoTrenordBuilder.costruisciTreno(sigla);
			
			for(Vagone v : lista) {
			    v.setId_treno(vagoneDao.find(id_treno));
			    v.setFabbrica(vagoneDao.find("TN"));	
			}
			
		}
	    
	    // Aggiungi i nuovi vagoni
	    for (Vagone v : lista) {
	        vagoneDao.add(v);
	    }
    }
	
	@Override
	public TrenoCompletoDTO findTrenoCompletoById(int idTreno) {
	    return trenoDao.findTrenoCompletoById(idTreno);
	}
	
  @Override
    public Treno duplicateTreno(String sigla) {
		// Recupera i dati del treno esistente
        TrenoCompletoDTO trenoCompleto = trenoDao.findTrenoCompletoBySigla(sigla);
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
   
        if (trenoCompleto.getFabbrica().getFabbrica().equals("Italo")) {
            listaVagoni = trenoItaloBuilder.costruisciTreno(nuovoTreno.getSigla());
        } else if (trenoCompleto.getFabbrica().getFabbrica().equals("Trenord")) {
            listaVagoni = trenoTrenordBuilder.costruisciTreno(nuovoTreno.getSigla());
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
  
	@Override
	public void acquistaBiglietti(int idTreno) throws Exception {
		trenoDao.decrementaBiglietti(trenoDao.findTreno(idTreno));
	}

	@Override
	public String invertiSigla(String siglaTreno) {
	    if (siglaTreno != null && siglaTreno.endsWith("H")) {
	        return new StringBuilder(siglaTreno).reverse().toString();
	    }else {
	        throw new IllegalArgumentException("La sigla del treno deve terminare con 'H' per poter essere invertita");
	    }
	}
}
