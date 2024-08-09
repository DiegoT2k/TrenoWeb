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
	public Vagone getLocomotiva(int id) {
	   //LocomotivaItalo l = new LocomotivaItalo();
//		BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class); 
//		locomotivaItalo = (LocomotivaItalo) factory.getBean(LocomotivaItalo.class);
//	    VagoneDao dao = factory.getBean("vagoneDao", VagoneDao.class); 
//	   
//	    locomotivaItalo.setLunghezza(100);
//	    locomotivaItalo.setPeso(100);
//	    locomotivaItalo.setPrezzo(100);
//	    locomotivaItalo.setId_treno(dao.find(id));
//	    locomotivaItalo.setFabbrica(dao.find("IT"));
//	    locomotivaItalo.setTipo(dao.findTipo("Locomotiva"));
//	    locomotivaItalo.setBiglietti(0);
	   
	    return locomotivaItalo;
	}

	@Override
	public Vagone getPasseggeri(int id) {
//		BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class); 
//		passeggeriItalo = (PasseggeriItalo) factory.getBean(PasseggeriItalo.class);
//	    VagoneDao dao = factory.getBean("vagoneDao", VagoneDao.class);
//	    
//	    passeggeriItalo.setLunghezza(100);
//	    passeggeriItalo.setPeso(100);
//	    passeggeriItalo.setPrezzo(100);
//	    passeggeriItalo.setId_treno(dao.find(id));
//	    passeggeriItalo.setFabbrica(dao.find("IT"));
//	    passeggeriItalo.setTipo(dao.findTipo("Passeggeri"));
//	    passeggeriItalo.setBiglietti(40);
	    
		return passeggeriItalo;
	}

	@Override
	public Vagone getCargo(int id) {
//		BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class); 
//		cargoItalo = (CargoItalo) factory.getBean(CargoItalo.class);
//	    VagoneDao dao = factory.getBean("vagoneDao", VagoneDao.class);
//	    
//	    cargoItalo.setLunghezza(100);
//	    cargoItalo.setPeso(100);
//	    cargoItalo.setPrezzo(100);
//	    cargoItalo.setId_treno(dao.find(id));
//	    cargoItalo.setFabbrica(dao.find("IT"));
//	    cargoItalo.setTipo(dao.findTipo("Cargo"));
//	    cargoItalo.setBiglietti(0);
	   
		return cargoItalo;
	}

	@Override
	public Vagone getRistorante(int id) {
//		BeanFactory factory = new AnnotationConfigApplicationContext(Beans.class); 
//		ristoranteItalo = (RistoranteItalo) factory.getBean(RistoranteItalo.class);
//	    VagoneDao dao = factory.getBean("vagoneDao", VagoneDao.class);
//	    
//	    ristoranteItalo.setLunghezza(100);
//	    ristoranteItalo.setPeso(100);
//	    ristoranteItalo.setPrezzo(100);
//	    ristoranteItalo.setId_treno(dao.find(id));
//	    ristoranteItalo.setFabbrica(dao.find("IT"));
//	    ristoranteItalo.setTipo(dao.findTipo("Ristorante"));
//	    ristoranteItalo.setBiglietti(2);
	    
		return ristoranteItalo;
	}

}
