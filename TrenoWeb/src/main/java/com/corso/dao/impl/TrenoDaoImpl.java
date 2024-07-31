package com.corso.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.corso.dao.TrenoDao;
import com.corso.model.Fabbrica;
import com.corso.model.Treno;
import com.corso.model.TrenoFilter;
import com.corso.model.Utente;
import com.corso.model.abs_vagone.Vagone;

import javax.persistence.*;

public class TrenoDaoImpl implements TrenoDao{
	
	@PersistenceContext // figlia di Autowired
	
	private EntityManager manager; 
	
	@Override
	public int add(Treno treno) {
		manager.persist(treno);
		return treno.getId_treno();
	}

	
	@Override
	public Utente find(int id) {
		return manager.find(Utente.class, id);
	}

	@Override
	public Fabbrica find(String sigla) {
		return manager.find(Fabbrica.class, sigla);
	}

	@Override
	public Treno findTreno(int id) {
		return manager.find(Treno.class, id);
	}
	
	@Override
	public List<Treno> findAll() {
		Query q = (Query) manager.createQuery("from Treno", Treno.class);
		@SuppressWarnings("unchecked")
		List<Treno> l = q.getResultList();
		return l;
	}
	
	
	
	/**
	 * PESO LUNGHEZZA PESO QUERY
	 * prezzoMin
	 * prezzoMax
	 * 
	 * lunghezzaMin
	 * lunghezzaMax
	 * 
	 * pesoMin
	 * pesoMax
	 * 
	 * CON CRITERIA
	 * filtro nome utente
	 * 
	 * cercare stringa all'interno delle sigle
	 * 
	 * 
	 */
	
	

	
	@Override
	public List<Treno> findByFilter(TrenoFilter filter){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Treno> criteriaQuery = criteriaBuilder.createQuery(Treno.class);
		Root<Treno> criteriaRoot= criteriaQuery.from(Treno.class);
		Join<Treno, Vagone> vagone = criteriaRoot.join("vagoni"); //vagoni da set vagoni in treno
		
//		String jpql = "SELECT id_treno, SUM(peso), SUM(prezzo), SUM(lunghezza)"
//				+ "FROM vagone +"
//				+ "GROUP BY id_treno";
//		Query q = manager.createQuery(jpql);
//		List<Treno> l = q.getResultList();
		
		Predicate prezzoMinPredicate = null, prezzoMaxPredicate = null,
				  lunghezzaMinPredicate = null, lunghezzaMaxPredicate = null,
				  pesoMinPredicate = null, pesoMaxPredicate = null;
		
		if (filter.getPrezzoMin()!= null) {
			prezzoMinPredicate = criteriaBuilder.greaterThanOrEqualTo(vagone.get("prezzo"), filter.getPrezzoMin());
		}
		
		if (filter.getPrezzoMax()!= null) {
			prezzoMaxPredicate = criteriaBuilder.lessThanOrEqualTo(vagone.get("prezzo"), filter.getPrezzoMax());
		}

		if (filter.getLunghezzaMin()!= null) {
			lunghezzaMinPredicate = criteriaBuilder.greaterThanOrEqualTo(vagone.get("lunghezza"), filter.getLunghezzaMin());
		}
		
		if (filter.getLunghezzaMax()!= null) {
			lunghezzaMaxPredicate = criteriaBuilder.lessThanOrEqualTo(vagone.get("lunghezza"), filter.getLunghezzaMax());
		}
		
		if (filter.getPesoMin()!= null) {
			pesoMinPredicate = criteriaBuilder.greaterThanOrEqualTo(vagone.get("peso"), filter.getPesoMin());
		}
		
		if (filter.getPesoMax()!= null) {
			pesoMaxPredicate = criteriaBuilder.lessThanOrEqualTo(vagone.get("peso"), filter.getPesoMax());
		}
		
		
		
		//Predicate range del prezzo
		Predicate prezzoRange  = null;
		if (prezzoMinPredicate != null && prezzoMaxPredicate != null) {
			prezzoRange = criteriaBuilder.and(prezzoMinPredicate, prezzoMaxPredicate);
		} else if (prezzoMinPredicate != null) {
			prezzoRange = prezzoMinPredicate;
		} else if (prezzoMaxPredicate != null) {
			prezzoRange = prezzoMaxPredicate;
		}
		
		
		//Predicate range della lunghezza
		Predicate lunghezzaRange = null;
		if (lunghezzaMinPredicate != null && lunghezzaMaxPredicate != null) {
			lunghezzaRange = criteriaBuilder.and(lunghezzaMinPredicate, lunghezzaMaxPredicate);
		} else if (lunghezzaMinPredicate != null) {
			lunghezzaRange = lunghezzaMinPredicate;
		} else if (lunghezzaMaxPredicate != null) {
			lunghezzaRange = lunghezzaMaxPredicate;
		}
		
		//Predicate range del peso
		Predicate pesoRange = null;
		if (pesoMinPredicate != null && pesoMaxPredicate != null) {
			pesoRange = criteriaBuilder.and(pesoMinPredicate, pesoMaxPredicate);
		} else if (pesoMinPredicate != null) {
			pesoRange = pesoMinPredicate;
		} else if (pesoMaxPredicate != null) {
			pesoRange = pesoMaxPredicate;
		}
		
		//Combinazione delle predicate 
		List<Predicate> predicates = new ArrayList<>();

		if(prezzoRange != null) {
			predicates.add(prezzoRange);
		}
		
		if(lunghezzaRange != null) {
			predicates.add(lunghezzaRange);
		}
		
		if(pesoRange != null) {
			predicates.add(pesoRange);
		}
		
		//Condizioni WHERE della query
		if (!predicates.isEmpty()) {
			criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		}
		
		//creare Query
		Query query = manager.createQuery(criteriaQuery);
		
		@SuppressWarnings("unchecked")
		List<Treno> result = query.getResultList();
	
		return result;
	}
	
	public double calcolaPrezzo(Treno t) {
		
		Set<Vagone> set = t.getVagoni();
		double tot = 0;
		for(Vagone v : set) {
			tot += v.getPrezzo();
		}
		
		return tot;
	}


	@Override
	public List<Utente> findByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

}
