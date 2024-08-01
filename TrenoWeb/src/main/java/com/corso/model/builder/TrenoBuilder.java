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
				lista.add( getPasseggeri(id_treno) );
				break;
			case 'C':
				// treno.addVagone( getCargo() );
				lista.add( getCargo(id_treno) );
				break;
			case 'R':
				// treno.addVagone( getRistorante() );
				lista.add( getRistorante(id_treno) );
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
	
	abstract protected Vagone getLocomotiva(int id);
	abstract protected Vagone getPasseggeri(int id);
	abstract protected Vagone getCargo(int id);
	abstract protected Vagone getRistorante(int id);
	
}
