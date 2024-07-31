package com.corso.model.builder.impl;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
	public Vagone getLocomotiva(int id) {
		
	   //LocomotivaItalo l = new LocomotivaItalo();
		BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class); 
		locomotivaTrenord = (LocomotivaTrenord) factory.getBean(LocomotivaTrenord.class);
	    VagoneDao dao = factory.getBean("vagoneDao", VagoneDao.class); 
	   
	    locomotivaTrenord.setLunghezza(50);
	    locomotivaTrenord.setPeso(50);
	    locomotivaTrenord.setPrezzo(50);
	    locomotivaTrenord.setId_treno(dao.find(id));
	    locomotivaTrenord.setFabbrica(dao.find("TN"));
	    locomotivaTrenord.setTipo(dao.findTipo("Locomotiva"));
	    locomotivaTrenord.setBiglietti(0);
	   
	    return locomotivaTrenord;
	}

	@Override
	public Vagone getPasseggeri(int id) {
		BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class); 
		passeggeriTrenord = (PasseggeriTrenord) factory.getBean(PasseggeriTrenord.class);
	    VagoneDao dao = factory.getBean("vagoneDao", VagoneDao.class);
	    
	    passeggeriTrenord.setLunghezza(50);
	    passeggeriTrenord.setPeso(50);
	    passeggeriTrenord.setPrezzo(50);
	    passeggeriTrenord.setId_treno(dao.find(id));
	    passeggeriTrenord.setFabbrica(dao.find("TN"));
	    passeggeriTrenord.setTipo(dao.findTipo("Passeggeri"));
	    passeggeriTrenord.setBiglietti(77);
	    
		return passeggeriTrenord;
	}

	@Override
	public Vagone getCargo(int id) {
		BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class); 
		cargoTrenord = (CargoTrenord) factory.getBean(CargoTrenord.class);
	    VagoneDao dao = factory.getBean("vagoneDao", VagoneDao.class);
	    
	    cargoTrenord.setLunghezza(50);
	    cargoTrenord.setPeso(50);
	    cargoTrenord.setPrezzo(50);
	    cargoTrenord.setId_treno(dao.find(id));
	    cargoTrenord.setFabbrica(dao.find("TN"));
	    cargoTrenord.setTipo(dao.findTipo("Cargo"));
	    cargoTrenord.setBiglietti(0);
	    
		return cargoTrenord;
	}

	@Override
	public Vagone getRistorante(int id) {
		BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class); 
		ristoranteTrenord = (RistoranteTrenord) factory.getBean(RistoranteTrenord.class);
	    VagoneDao dao = factory.getBean("vagoneDao", VagoneDao.class);
	    
	    ristoranteTrenord.setLunghezza(50);
	    ristoranteTrenord.setPeso(50);
	    ristoranteTrenord.setPrezzo(50);
	    ristoranteTrenord.setId_treno(dao.find(id));
	    ristoranteTrenord.setFabbrica(dao.find("TN"));
	    ristoranteTrenord.setTipo(dao.findTipo("Ristorante"));
	    ristoranteTrenord.setBiglietti(3);
	    
		return ristoranteTrenord;
	}

}