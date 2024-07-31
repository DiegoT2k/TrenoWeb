package com.corso.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.corso.dao.FabbricaDao;
import com.corso.dao.TipologiaDao;
import com.corso.dao.TrenoDao;
import com.corso.dao.impl.FabbricaDaoImpl;
import com.corso.dao.impl.TipologiaDaoImpl;
import com.corso.dao.impl.TrenoDaoImpl;
import com.corso.dao.impl.UtenteDaoImpl;
import com.corso.dao.impl.VagoneDaoImpl;
import com.corso.dao.impl.ValutazioneDaoImpl;
import com.corso.model.abs_vagone.Vagone;
import com.corso.model.builder.TrenoBuilder;
import com.corso.model.builder.impl.TrenoItaloBuilder;
import com.corso.model.vagone.impl.LocomotivaItalo;
import com.corso.dao.UtenteDao;
import com.corso.dao.VagoneDao;
import com.corso.dao.ValutazioneDao;

@Configuration
@ComponentScan(basePackages="com.corso.spring.annotation")
@EnableTransactionManagement
public class Beans {
    
@Bean(name="dataSource")
	public DataSource getDataSource () {
		
		DriverManagerDataSource ds = new DriverManagerDataSource(); 
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("progettoSweng");
		ds.setUrl("jdbc:mysql://127.0.0.1:3306/treno2");
		return ds; 
	} 

@Bean(name="entityManager")
public LocalContainerEntityManagerFactoryBean getEntityManager(){
	
	LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	 // JDBC
	 factory.setDataSource(getDataSource());  
	 
	 // imposta il dialogo tra JPA e hibernate
	 factory.setJpaVendorAdapter(getJpaVendorAdapter()); // imposta il dialogo tra JPA e hibernate
	 
	 // impostare il luogo dove si trovano i bean (entità del DB)
	 //factory.setPackagesToScan(this.getClass().getPackage().getName()); 
	 factory.setPackagesToScan("com.corso.model", "com.corso.model.builder.impl"); 
	 return factory; 
} 	

private HibernateJpaVendorAdapter getJpaVendorAdapter() {
	HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
	adapter.setDatabase(Database.MYSQL);   // obbligatorio: serve per tradurre le query nel particolare Dialetto
	
	adapter.setGenerateDdl(true);          //facoltativo, attiva il DDL cio� hibernate creaer� le strutture nel DB se non sono gi� essitenti
	adapter.setShowSql(true);              // mostra l'SQL, comodo per i corsi e per il debug ma in produzione solitamente � a false 
	return adapter;
}	

/**** transazioni ****/
@Bean
public PlatformTransactionManager getTransactionManager(){
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(getEntityManager().getObject());
      return transactionManager;
}

/**** sezione DAO ****/

@Bean(name="trenoItaloBuilder")
public TrenoBuilder getTrenoItaloBuilder() {
	return new TrenoItaloBuilder();
}

/**
@Bean(name="locomotivaItalo")
public Vagone getLocomotivaItalo() {
	Vagone l = new LocomotivaItalo();

//	VagoneDao dao = new VagoneDaoImpl();
	
	l.setLunghezza(1);
	l.setPeso(1);
	l.setPrezzo(1);
	//l.setId_treno(dao.find(3));
	//l.setFabbrica(dao.find("TN"));
	//l.setTipo(dao.findTipo("Locomotiva"));
	
	return l;
}
**/
@Bean(name="utenteDao") 
public UtenteDao getUtenteDao (){
	UtenteDao dao = new UtenteDaoImpl();
	return dao; 
}

@Bean(name="trenoDao") 
public TrenoDao getTrenoDao (){
	TrenoDao dao = new TrenoDaoImpl();
	return dao; 
}

@Bean(name="fabbricaDao")
public FabbricaDao getFabbricaDao() {
	FabbricaDao dao = new FabbricaDaoImpl();
	return dao;
}

@Bean(name="vagoneDao")
public VagoneDao getVagoneDao() {
	VagoneDao dao = new VagoneDaoImpl();
	return dao;
}

@Bean(name="tipologiaDao")
public TipologiaDao getTipologiaDao() {
	TipologiaDao dao = new TipologiaDaoImpl();
	return dao;
}

@Bean(name="valutazioneDao")
public ValutazioneDao getValutazione() {
	ValutazioneDao dao = new ValutazioneDaoImpl();
	return dao;
}

}
