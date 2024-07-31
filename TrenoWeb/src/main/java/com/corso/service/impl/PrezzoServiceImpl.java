package com.corso.service.impl;

import java.util.Set;

import com.corso.dao.impl.TrenoDaoImpl;
import com.corso.model.Treno;
import com.corso.model.abs_vagone.Vagone;

public class PrezzoServiceImpl {

	TrenoDaoImpl tDao = null;
	
	public double calcolaPrezzo(Treno t) {
		
		Set<Vagone> set = t.getVagoni();
		double tot = 0;
		for(Vagone v : set) {
			tot += v.getPrezzo();
		}
		
		return tot;
	}
	
	public void filtraPrezzo(){
		
		
		
	}
	
}
