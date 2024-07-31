package com.corso.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import com.corso.dao.Dao;
import com.corso.model.Bean;
import com.corso.model.Fabbrica;



public /*abstract*/ class DaoImpl implements Dao {
	
	@PersistenceContext
	EntityManager manager;
	

	public  List<?> all(Class c) {
		String jpql = "from Prodotto";  
	    Query q =  manager.createQuery(jpql,c);
		@SuppressWarnings("unchecked")
		List<?> l = q.getResultList();
		return l;
	}

	

	public Object find(Class c, Integer id) {
		Object o = manager.find(c, id);
		return o;
	}
	
	public void persist(Bean bean) {
		// altro da fare
		manager.persist(bean);
	}
	
	protected List<? extends Bean> findByField (String field, Class c, String parameter, String value){
		String jpql = "from " + c.getSimpleName() 
				+" where "+ field +" =:"+ parameter +"";  
		System.out.println("jpql: " + jpql);
		
		
	    Query q =  manager.createQuery(jpql, c);
	    
	    q.setParameter(parameter,value); 
	    
		@SuppressWarnings("unchecked")
		List<? extends Bean> l = q.getResultList();
		return l;
	}	
	
	

}