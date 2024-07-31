package com.corso.base;

import com.corso.dao.TipologiaDao;
import com.corso.dao.TrenoDao;
import com.corso.dao.UtenteDao;
import com.corso.dao.VagoneDao;
import com.corso.dao.ValutazioneDao;
import com.corso.model.Fabbrica;
import com.corso.model.Tipologia;
import com.corso.model.Treno;
import com.corso.model.Utente;
import com.corso.model.Valutazione;
import com.corso.model.abs_vagone.Vagone;
import com.corso.model.vagone.impl.LocomotivaItalo;
import com.corso.model.vagone.impl.LocomotivaTrenord;

public class BaseTest {

protected static void stampa(String nomeMetodoTest) {
		   System.out.println("-----------------------------------------------------------");
		   System.out.println("invocazione del metodo: " + nomeMetodoTest + "()");
		   System.out.println("-----------------------------------------------------------");
	   }
   
		protected static Utente getUtente() {
			   Utente utente = new Utente();
			   utente.setNome("Eren");
			   utente.setCognome("Jaeger");
			   utente.setEmail("erenj@gmail.com");
			   utente.setUsername("ErenIlGigante");
			   utente.setPassword("Rumbling");
			   return utente;
		   }	
		   
		protected static Treno getTreno(TrenoDao dao) {
			   Treno treno = new Treno();
			   treno.setId_utente(dao.find(12));
			   treno.setFabbrica(dao.find("ITA"));
			   treno.setBiglietti(16);
	
			   return treno;
	   }		
		
		protected static Fabbrica getFabbrica() {
			Fabbrica fabbrica = new Fabbrica();
			fabbrica.setSigla("FR");
			fabbrica.setFabbrica("Frecciarossa");
			
			return fabbrica;
		}
		
		protected static void getSetTreni(Utente utente) {
			System.out.println(utente.getTreni());
		   }	
		
		
//		protected static Vagone getVagone(VagoneDao dao) {
//			Vagone locomotivaItalo = new LocomotivaItalo();
//
//			locomotivaItalo.setId_treno(dao.find(22));
//			locomotivaItalo.setLunghezza(14);
//			locomotivaItalo.setPeso(40);
//			locomotivaItalo.setPrezzo(130);
//			locomotivaItalo.setFabbrica(dao.find("ITA"));
//			locomotivaItalo.setTipo(dao.findTipo("Locomotiva"));
//			
//			return locomotivaItalo;
//		}

		protected static Vagone getVagone(VagoneDao dao) {
			Vagone locomotivaTrenord = new LocomotivaTrenord();
			
			locomotivaTrenord.setLunghezza(40);
			locomotivaTrenord.setPeso(23);
			locomotivaTrenord.setPrezzo(100);
			locomotivaTrenord.setId_treno(dao.find(17));
			locomotivaTrenord.setFabbrica(dao.find("TN"));
			locomotivaTrenord.setTipo(dao.findTipo("Locomotiva"));
			
			return locomotivaTrenord;
		}
		
		protected static Tipologia getTipologia() {
			Tipologia tipologia = new Tipologia();
//			tipologia.setTipo("Locomotiva");
//			tipologia.setTipo("Passeggeri");
//			tipologia.setTipo("Ristorante");
//			tipologia.setTipo("Cargo");
			return tipologia;
		}

		protected static void getSetVagone(Treno treno) {
			System.out.println(treno.getVagoni());
		   }

		protected static Valutazione getVoto(ValutazioneDao dao) {
			Valutazione voto = new Valutazione();
			voto.setVoto(4);
			voto.setUtente(dao.findUtente(1));
			voto.setTreno(dao.findTreno(1));
			
			return voto;
			
		}
		
		protected static void getSetValutazioni(Treno treno) {
			System.out.println(treno.getValutazioni());
		   }

}


