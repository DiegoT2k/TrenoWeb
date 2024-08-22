package com.corso.model.builder.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.corso.model.builder.TrenoBuilder;
import com.corso.model.vagone.impl.*;
import com.corso.model.abs_vagone.Vagone;

@Component
public class TrenoTrenordBuilder extends TrenoBuilder{
	
    @Autowired
    private LocomotivaTrenord locomotivaTrenord;
    
    @Autowired
    private PasseggeriTrenord passeggeriTrenord;
    
    @Autowired
    private CargoTrenord cargoTrenord;
    
    @Autowired
    private RistoranteTrenord ristoranteTrenord;
    
	@Override
	public Vagone getLocomotiva() {
	    return locomotivaTrenord;
	}

	@Override
	public Vagone getPasseggeri() {
		return passeggeriTrenord;
	}

	@Override
	public Vagone getCargo() {
		return cargoTrenord;
	}

	@Override
	public Vagone getRistorante() {	    
		return ristoranteTrenord;
	}

}