package com.corso.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.corso.dao.ValutazioneDao;
import com.corso.dto.TrenoCompleto;
import com.corso.model.Treno;
import com.corso.model.Utente;
import com.corso.model.Valutazione;

public class ValutazioneDaoImpl extends DaoImpl implements ValutazioneDao{
	
	@PersistenceContext // figlia di Autowired
	
	private EntityManager manager;  
	
	@Override
	public Valutazione add(Valutazione voto) {
		manager.persist(voto);
		return voto;
	}
	
	public Utente findUtente(int id_utente) {
		Utente u = manager.find(Utente.class, id_utente);
		return u;
	}
	
	public Treno findTreno(int id_treno) {
		Treno t = manager.find(Treno.class, id_treno);
		return t;
	}
	
	@Override
	public void addVoto(int rating, int id_treno, int id_utente) {
		Valutazione v = new Valutazione();
		v.setId_treno(id_treno); v.setId_utente(id_utente); v.setVoto(rating);
		manager.merge(v);
	}

	// Cerca il voto, se � gia presente lo rimuove in modo da poter inserire quello nuovo
	@Override
	public void findVoto(int id_treno, int id_utente) {
		
		String jpql = "SELECT v "
				+ "FROM Valutazione v "
				+ "WHERE v.id_treno = :id_treno AND v.id_utente = :id_utente" ;
		
		Query q = manager.createQuery(jpql);
		
		q.setParameter("id_utente", id_utente);
		q.setParameter("id_treno", id_treno);
		List<Valutazione> l = q.getResultList();
		
		if(!l.isEmpty()) {
		    Valutazione valutazioneDaEliminare = l.get(0);
		   
		    manager.remove(valutazioneDaEliminare);
		    
		}
		
	}

	@Override
	public List<Valutazione> findByValutazione(Valutazione voto) {
		Query q = (Query) manager.createQuery("from Valutazione where voto=:voto", Valutazione.class);
		q.setParameter("voto", voto);
		
		List<Valutazione> l = q.getResultList();
		
		return l;
		
	}

	@Override
	public List<Valutazione> getAll() {
		
		String jpql = "SELECT v FROM Valutazione v ";
		Query q = manager.createQuery(jpql, Valutazione.class);
		
		return q.getResultList();
	}

	@Override
	public Valutazione findById(int id) {
		Valutazione v = manager.find(Valutazione.class, id);
		return v;
	}
	
	
}
