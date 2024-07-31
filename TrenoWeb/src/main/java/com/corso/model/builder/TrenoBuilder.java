package com.corso.model.builder;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.corso.model.abs_vagone.Vagone;
import com.corso.exception.DimensioneMagException;
import com.corso.exception.DimensioneMinException;
import com.corso.exception.LocomotivaNonInTestaException;
import com.corso.exception.NumLocomotiveException;
import com.corso.exception.NumRistorantiException;
import com.corso.exception.SoloCargoException;
import com.corso.exception.VagoneException;
import com.corso.model.Treno;

@Transactional
public abstract class TrenoBuilder {
	
	//@Autowired
	//private Treno treno;
	

	/**
	public Treno creaTreno(String stringa){
		Treno t = null;
		
		try{
			t = costruisciTreno(stringa.toUpperCase());
		}catch(TrenoException l){
			System.out.println(l.getSuggerimento());
		}
		
		return t;
	}
	**/
	
	public List<Vagone> costruisciTreno(String vagoni, int id_treno){
		
		//Treno treno = new Treno();	
		
		List<Vagone> lista = new ArrayList<Vagone>();
		
		//checkStringa(vagoni);

		for(char vagone : vagoni.toCharArray()){
			switch (vagone) {
			case 'H':
				// treno.addVagone( getLocomotiva() );
				lista.add( getLocomotiva(id_treno) );
				break;
			case 'P':
				// treno.addVagone( getPasseggeri() );
				lista.add( getPasseggeri() );
				break;
			case 'C':
				// treno.addVagone( getCargo() );
				lista.add( getCargo() );
				break;
			case 'R':
				// treno.addVagone( getRistorante() );
				lista.add( getRistorante() );
			default:
				break;
			}
		}
		
		//treno.setVagoni(lista);
		
		//for(Vagone v : lista) {
			//System.out.println(v);
		//}
		
		return lista;
	}
/**
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
	**/
	
	abstract protected Vagone getLocomotiva(int id);
	abstract protected Vagone getPasseggeri();
	abstract protected Vagone getCargo();
	abstract protected Vagone getRistorante();
	
}
