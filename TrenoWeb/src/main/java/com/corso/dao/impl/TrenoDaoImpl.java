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
import com.corso.dto.TrenoCompleto;
import com.corso.dto.TrenoVoto;
import com.corso.model.Fabbrica;
import com.corso.model.Treno;
import com.corso.model.TrenoFilter;
import com.corso.model.Utente;
import com.corso.model.Valutazione;
import com.corso.model.abs_vagone.Vagone;
import com.corso.vo.FiltroVO;

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
		
	@Override
	public List<TrenoCompleto> findTrenoCompleto(){
		
		String jpql = "SELECT new com.corso.dto.TrenoCompleto(t.id_treno, t.sigla, t.id_utente, t.fabbrica, AVG(val.voto), SUM(vag.peso), SUM(vag.prezzo), SUM(vag.lunghezza), SUM(vag.biglietti) ) "
				+ "FROM Treno t "
				+ "LEFT JOIN t.valutazione val "
				+ "LEFT JOIN t.vagoni vag "
				+ "GROUP BY t.id_treno" ;
		
		Query q = manager.createQuery(jpql);
		
		List<TrenoCompleto> l = q.getResultList();
		
		return l;
	}

	@Override
	public List<TrenoCompleto> filtraTrenoCompleto(FiltroVO filtroVO, Utente utente) {
	    StringBuilder jpql = new StringBuilder("SELECT new com.corso.dto.TrenoCompleto(t.id_treno, t.sigla, t.id_utente, t.fabbrica, AVG(val.voto), SUM(vag.peso), SUM(vag.prezzo), SUM(vag.lunghezza), SUM(vag.biglietti) ) "
	            + "FROM Treno t "
	            + "LEFT JOIN t.valutazione val "
	            + "LEFT JOIN t.vagoni vag "
	            + "GROUP BY t.id_treno "
	            + "HAVING 1 = 1"); // start with a true condition

	    if (filtroVO.getPrezzoMin() != null) {
	        jpql.append(" AND SUM(vag.prezzo) >= :prezzoMin");
	    }
	    if (filtroVO.getPrezzoMax() != null) {
	        jpql.append(" AND SUM(vag.prezzo) <= :prezzoMax");
	    }
	    if (filtroVO.getLunghezzaMin() != null) {
	        jpql.append(" AND SUM(vag.lunghezza) >= :lunghezzaMin");
	    }
	    if (filtroVO.getLunghezzaMax() != null) {
	        jpql.append(" AND SUM(vag.lunghezza) <= :lunghezzaMax");
	    }
	    if (filtroVO.getPesoMin() != null) {
	        jpql.append(" AND SUM(vag.peso) >= :pesoMin");
	    }
	    if (filtroVO.getPesoMax() != null) {
	        jpql.append(" AND SUM(vag.peso) <= :pesoMax");
	    }
	    if(filtroVO.getSigla() != null) {
	    	jpql.append(" AND t.sigla LIKE :sigla");
	    }
	    if(utente != null) {
	    	jpql.append(" AND t.id_utente = :utente");
	    }

	    /*
	     * Double prezzoMin, Double prezzoMax, Double lunghezzaMin, 
			Double lunghezzaMax, Double pesoMin, Double pesoMax, String sigla, Utente utente,
			String sortField, String sortOrder
	     * */
	    
	    if (filtroVO.getSortField() != null && filtroVO.getSortOrder() != null) {
	        jpql.append(" ORDER BY ");
	        switch (filtroVO.getSortField()) {
	            case "lunghezza":
	                jpql.append("SUM(vag.lunghezza)");
	                break;
	            case "peso":
	                jpql.append("SUM(vag.peso)");
	                break;
	            case "prezzo":
	                jpql.append("SUM(vag.prezzo)");
	                break;
	            default:
	                jpql.append("t.id_treno"); // Ordinamento predefinito
	                break;
	        }
	        jpql.append(" " + filtroVO.getSortOrder());
	    }
	    
	    
	    Query q = manager.createQuery(jpql.toString());

	    if (filtroVO.getPrezzoMin() != null) {
	        q.setParameter("prezzoMin", filtroVO.getPrezzoMin());
	    }
	    if (filtroVO.getPrezzoMax() != null) {
	        q.setParameter("prezzoMax", filtroVO.getPrezzoMax());
	    }
	    if (filtroVO.getLunghezzaMin() != null) {
	        q.setParameter("lunghezzaMin", filtroVO.getLunghezzaMin());
	    }
	    if (filtroVO.getLunghezzaMax() != null) {
	        q.setParameter("lunghezzaMax", filtroVO.getLunghezzaMax());
	    }
	    if (filtroVO.getPesoMin() != null) {
	        q.setParameter("pesoMin", filtroVO.getPesoMin());
	    }
	    if (filtroVO.getPesoMax() != null) {
	        q.setParameter("pesoMax", filtroVO.getPesoMax());
	    }
	    if(filtroVO.getSigla() != null) {
	    	q.setParameter("sigla", "%" + filtroVO.getSigla() + "%");
	    }
	    if(utente != null) {
	    	q.setParameter("utente", utente);
	    }
	    
	    List<TrenoCompleto> l = q.getResultList();

	    return l;
	}

	
	@Override
	public List<Valutazione> findAllValutazione(){
		Query q = (Query) manager.createQuery("from Valutazione", Valutazione.class);

		List<Valutazione> l = q.getResultList();
		return l;
	}
	
	
	public List<Object[]> filterByPeso(/**TrenoFilter filter**/){
		
		String jpql = "SELECT t.id_treno, SUM(v.peso) AS pesoTotale "
				+ "FROM Treno t "
				+ "JOIN t.vagoni v "
				+ "GROUP BY t.id_treno " ;
				//+ "HAVING SUM(v.peso) >= 300";
		
		Query q = manager.createQuery(jpql);
		
		List<Object[]> l = q.getResultList();
		

		return l;
	}
	
	@Override
	public List<Utente> findByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TrenoCompleto> findByIdUtente(int utenteId) {
		String jpql = "SELECT new com.corso.dto.TrenoCompleto(t.id_treno, t.sigla, t.id_utente, t.fabbrica, AVG(val.voto), SUM(vag.peso), SUM(vag.prezzo), SUM(vag.lunghezza), SUM(vag.biglietti)) "
	            + "FROM Treno t "
	            + "LEFT JOIN t.valutazione val "
	            + "LEFT JOIN t.vagoni vag "
	            + "WHERE t.id_utente.id_utente = :utenteId "
	            + "GROUP BY t.id_treno";
	    
	    Query q = manager.createQuery(jpql);
	    q.setParameter("utenteId", utenteId);
	    
	    return q.getResultList();
	}
	
	@Override
    public void updateTreno(Treno treno) {
        manager.merge(treno);
    }
	
	@Override
    public void deleteTreno(int idTreno) {
		Treno treno = manager.find(Treno.class, idTreno);
	    if (treno != null) {
	        manager.remove(treno);
	    }
    }
	
	@Override
	public void deleteVagoniByTreno(Treno treno) {
		String hql = "DELETE FROM Vagone v WHERE v.id_treno.id = :id_treno";
	    Query query = manager.createQuery(hql);
	    query.setParameter("id_treno", treno.getId_treno());
	    int deleted = query.executeUpdate();
	    System.out.println("num vagoni eliminati: " + deleted);
	}
	
	@Override
    public TrenoCompleto findTrenoCompletoById(int idTreno) {
        String hql = "SELECT new com.corso.dto.TrenoCompleto(t.id_treno, t.sigla, t.id_utente, t.fabbrica, AVG(val.voto), SUM(vag.peso), SUM(vag.prezzo), SUM(vag.lunghezza), SUM(vag.biglietti)) " 
                    + "FROM Treno t " 
                    + "LEFT JOIN t.valutazione val "
     	            + "LEFT JOIN t.vagoni vag "
                    + "WHERE t.id_treno = :idTreno";

        Query query = manager.createQuery(hql);
        query.setParameter("idTreno", idTreno);

        return (TrenoCompleto) query.getSingleResult();
    }
	
	public TrenoCompleto findTrenoCompletoBySigla(String sigla) {
	    String jpql = "SELECT new com.corso.dto.TrenoCompleto(t.id_treno, t.sigla, t.id_utente, t.fabbrica, AVG(val.voto), SUM(vag.peso), SUM(vag.prezzo), SUM(vag.lunghezza), SUM(vag.biglietti)) "
	                + "FROM Treno t "
	                + "LEFT JOIN t.valutazione val "
	                + "LEFT JOIN t.vagoni vag "
	                + "WHERE t.sigla = :sigla "
	                + "GROUP BY t.id_treno";

	    Query query = manager.createQuery(jpql);
	    query.setParameter("sigla", sigla);

	    return (TrenoCompleto) query.getSingleResult();
	}

  @Override
	public void decrementaBiglietti(Treno idTreno) throws Exception {
		
		String jpql = "FROM Vagone v WHERE v.id_treno = :id_treno";
		Query query = manager.createQuery(jpql);
		query.setParameter("id_treno", idTreno);
		
		List<Vagone> lista = query.getResultList();
		for(Vagone v : lista) {
			if(v.getBiglietti() > 0) {
				int minusB = 1;
				System.out.println("biglietti da togliere " + minusB);
				v.setBiglietti(v.getBiglietti() - minusB);
				System.out.println("i biglietti del vagone sono: " + v.getBiglietti());
				manager.merge(v);
				return;
			}
		}
		
		throw new Exception("Biglietti esauriti");
	}

}
