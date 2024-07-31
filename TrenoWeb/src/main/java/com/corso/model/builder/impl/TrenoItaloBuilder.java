package com.corso.model.builder.impl;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import com.corso.model.builder.TrenoBuilder;
import com.corso.model.vagone.impl.*;
import com.corso.config.Beans;
import com.corso.dao.TrenoDao;
import com.corso.dao.VagoneDao;
import com.corso.model.Treno;
import com.corso.model.abs_vagone.Vagone;

@Component
public class TrenoItaloBuilder extends TrenoBuilder{
	/**
    @Autowired
    private LocomotivaItalo locomotivaItalo;
    
    @Autowired
    private PasseggeriItalo passeggeriItalo;
    
    @Autowired
    private CargoItalo cargoItalo;
    
    @Autowired
    private RistoranteItalo ristoranteItalo;
    **/
	

	
	@Override
	public Vagone getLocomotiva(int id) {
		
	   LocomotivaItalo l = new LocomotivaItalo();

	   BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class); 
	   VagoneDao dao = factory.getBean("vagoneDao", VagoneDao.class); 
	   l.setLunghezza(100);
	   l.setPeso(100);
	   l.setPrezzo(100);
	   l.setId_treno(dao.find(id));
	   l.setFabbrica(dao.find("IT"));
	   l.setTipo(dao.findTipo("Locomotiva"));
	   //l.setBiglietti(0);
	   
	   return l;
	}
/**
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
**/
}
