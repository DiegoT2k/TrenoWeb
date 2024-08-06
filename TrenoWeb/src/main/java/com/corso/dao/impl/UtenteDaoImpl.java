package com.corso.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.corso.dao.UtenteDao;
import com.corso.model.Utente;


public class UtenteDaoImpl extends DaoImpl implements UtenteDao{

	@PersistenceContext // figlia di Autowired
	
	private EntityManager manager;  
	
	@Override
	public Utente add(Utente utente) {
		manager.persist(utente);
		return utente;
	}
	
	@Override
	public List<Utente> findByUsername(String username) {
		Query q = (Query) manager.createQuery("from Utente where username=:username", Utente.class);
		q.setParameter("username", username);
		
		List<Utente> l = q.getResultList();
		
		return l;
	}

	@Override
	public List<Utente> findByEmail(String email) {
		Query q = (Query) manager.createQuery("from Utente where email=:email", Utente.class);
		q.setParameter("email", email);
		
		List<Utente> l = q.getResultList();
		return l;
	}
	
	@Override
    public Utente findById(int id) {
        return manager.find(Utente.class, id);
    }

//	@Override
//	public Utente findUsername(String username) {
//        String hql = "FROM Utente WHERE username = :username";
//        return manager.createQuery(hql, Utente.class)
//                            .setParameter("username", username)
//                            .getSingleResult();
//	}
//
//	

}


