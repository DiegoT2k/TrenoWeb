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
import com.corso.model.Treno;
import com.corso.model.builder.TrenoBuilder;
import com.corso.model.builder.impl.TrenoItaloBuilder;
import com.corso.model.builder.impl.TrenoTrenordBuilder;
import com.corso.model.vagone.impl.CargoItalo;
import com.corso.model.vagone.impl.CargoTrenord;
import com.corso.model.vagone.impl.LocomotivaItalo;
import com.corso.model.vagone.impl.LocomotivaTrenord;
import com.corso.model.vagone.impl.PasseggeriItalo;
import com.corso.model.vagone.impl.PasseggeriTrenord;
import com.corso.model.vagone.impl.RistoranteItalo;
import com.corso.model.vagone.impl.RistoranteTrenord;
import com.corso.service.TrenoService;
import com.corso.service.UserService;
import com.corso.service.ValutazioneService;
import com.corso.service.impl.TrenoServiceImpl;
import com.corso.service.impl.UserServiceImpl;
import com.corso.service.impl.ValutazioneServiceImpl;
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
	
	
	/** SEZIONE SERVICE **/
	
	
	@Bean(name="trenoService")
	public TrenoService getTrenoService() {
		TrenoService trenoService = new TrenoServiceImpl();
		return trenoService;
	}
	
	@Bean(name="userService")
	public UserService getUserService() {
		UserService userService = new UserServiceImpl();
		return userService;
	}
	
	@Bean(name="valutazioneService")
	public ValutazioneService getValutazioneService() {
		ValutazioneService valutazioneService = new ValutazioneServiceImpl();
		return valutazioneService ;
	}
	
	/** SEZIONE TRENO **/
	@Bean(name="treno")
	public Treno getTreno() {
		return new Treno();
	}
	
	@Bean(name="trenoItaloBuilder")
	public TrenoBuilder getTrenoItaloBuilder() {
		return new TrenoItaloBuilder();
	}
	
	@Bean(name="trenoTrenordBuilder")
	public TrenoBuilder getTrenoTrenordBuilder() {
		return new TrenoTrenordBuilder();
	}
	
	@Bean(name="locomotivaItalo")
	public LocomotivaItalo getLocomotivaItalo() {	
		LocomotivaItalo locomotivaItalo = new LocomotivaItalo();
	    locomotivaItalo.setLunghezza(20);
	    locomotivaItalo.setPeso(10);
	    locomotivaItalo.setPrezzo(2356);
	    locomotivaItalo.setBiglietti(0);
	    locomotivaItalo.setTipo(getVagoneDao().findTipo("Locomotiva"));
		return locomotivaItalo;
	}
	
	@Bean(name="locomotivaTrenord")
	public LocomotivaTrenord getLocomotivaTrenord() {	
		LocomotivaTrenord locomotivaTrenord = new LocomotivaTrenord();
		locomotivaTrenord.setLunghezza(12);
		locomotivaTrenord.setPeso(10);
		locomotivaTrenord.setPrezzo(5110);
		locomotivaTrenord.setBiglietti(0);
		locomotivaTrenord.setTipo(getVagoneDao().findTipo("Locomotiva"));
		return locomotivaTrenord;
	}
	
	@Bean(name="passeggeriItalo")
	public PasseggeriItalo getPasseggeriItalo() {
		PasseggeriItalo passeggeriItalo = new PasseggeriItalo();
		passeggeriItalo.setLunghezza(23);
		passeggeriItalo.setPeso(8);
		passeggeriItalo.setPrezzo(1112);
		passeggeriItalo.setBiglietti(200);
		passeggeriItalo.setTipo(getVagoneDao().findTipo("Passeggeri"));
		return passeggeriItalo;
	}
	
	@Bean(name="passeggeriTrenord")
	public PasseggeriTrenord getPasseggeriTrenord() {
		PasseggeriTrenord passeggeriTrenord = new PasseggeriTrenord();
		passeggeriTrenord.setLunghezza(28);
		passeggeriTrenord.setPeso(9);
		passeggeriTrenord.setPrezzo(6621);
		passeggeriTrenord.setBiglietti(241);
		passeggeriTrenord.setTipo(getVagoneDao().findTipo("Passeggeri"));
		return passeggeriTrenord;
	}
	
	@Bean(name="cargoItalo")
	public CargoItalo getCargoItalo() {
		CargoItalo cargoItalo = new CargoItalo();
		cargoItalo.setLunghezza(25);
	    cargoItalo.setPeso(15);
	    cargoItalo.setPrezzo(1030);
	    cargoItalo.setBiglietti(0);
	    cargoItalo.setTipo(getVagoneDao().findTipo("Cargo"));
		return cargoItalo; 
	}
	
	@Bean(name="cargoTrenord")
	public CargoTrenord getCargoTrenord() {
		CargoTrenord cargoTrenord = new CargoTrenord();
		cargoTrenord.setLunghezza(21);
		cargoTrenord.setPeso(16);
		cargoTrenord.setPrezzo(8311);
		cargoTrenord.setBiglietti(0);
		cargoTrenord.setTipo(getVagoneDao().findTipo("Cargo"));
		return cargoTrenord;
	}
	
	@Bean(name="ristoranteItalo")
	public RistoranteItalo getRistoranteItalo() {
		RistoranteItalo ristoranteItalo = new RistoranteItalo();
		ristoranteItalo.setLunghezza(19);
		ristoranteItalo.setPeso(11);
	    ristoranteItalo.setPrezzo(1050);
	    ristoranteItalo.setBiglietti(15);
	    ristoranteItalo.setTipo(getVagoneDao().findTipo("Ristorante"));
		return ristoranteItalo; 
	}

	@Bean(name="ristoranteTrenord")
	public RistoranteTrenord getRistoranteTrenord() {
		RistoranteTrenord ristoranteTrenord = new RistoranteTrenord();
		ristoranteTrenord.setLunghezza(54);
		ristoranteTrenord.setPeso(12);
		ristoranteTrenord.setPrezzo(1410);
		ristoranteTrenord.setBiglietti(12);
		ristoranteTrenord.setTipo(getVagoneDao().findTipo("Ristorante"));
		return new RistoranteTrenord();
	}

}
