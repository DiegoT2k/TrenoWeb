package com.corso.model.builder;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import com.corso.model.abs_vagone.Vagone;

@Transactional
public abstract class TrenoBuilder {
	
	public List<Vagone> costruisciTreno(String vagoni){
		
		//Treno treno = new Treno();	
		
		List<Vagone> lista = new ArrayList<Vagone>();
		
		//checkStringa(vagoni);

		for(char vagone : vagoni.toCharArray()){
			switch (vagone) {
			case 'H':
				// treno.addVagone( getLocomotiva() );
				lista.add( getLocomotiva() );
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
		
		return lista;
	}
	
	abstract protected Vagone getLocomotiva();
	abstract protected Vagone getPasseggeri();
	abstract protected Vagone getCargo();
	abstract protected Vagone getRistorante();
	
}
