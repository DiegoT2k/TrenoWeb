package com.corso.model.builder.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.corso.model.builder.TrenoBuilder;
import com.corso.model.vagone.impl.*;
import com.corso.model.abs_vagone.Vagone;

@Component
public class TrenoItaloBuilder extends TrenoBuilder{
	
    @Autowired
    private LocomotivaItalo locomotivaItalo;
    
    @Autowired
    private PasseggeriItalo passeggeriItalo;
    
    @Autowired
    private CargoItalo cargoItalo;
    
    @Autowired
    private RistoranteItalo ristoranteItalo;
    
	@Override
	public Vagone getLocomotiva() {   
	    return locomotivaItalo;
	}

	@Override
	public Vagone getPasseggeri() {  
		return passeggeriItalo;
	}

	@Override
	public Vagone getCargo() { 
		return cargoItalo;
	}

	@Override
	public Vagone getRistorante() {   
		return ristoranteItalo;
	}

}
