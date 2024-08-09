package com.corso.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.corso.dao.TrenoDao;
import com.corso.dto.TrenoCompletoDTO;
import com.corso.model.Fabbrica;
import com.corso.model.Treno;
import com.corso.model.Utente;
import com.corso.model.Valutazione;
import com.corso.model.abs_vagone.Vagone;
import com.corso.dto.FiltroDTO;
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
	public List<TrenoCompletoDTO> findTrenoCompleto(){
		
		String jpql = "SELECT new com.corso.dto.TrenoCompletoDTO(t.id_treno, t.sigla, t.id_utente, t.fabbrica, AVG(val.voto), SUM(vag.peso), SUM(vag.prezzo), SUM(vag.lunghezza), SUM(vag.biglietti) ) "
				+ "FROM Treno t "
				+ "LEFT JOIN t.valutazione val "
				+ "LEFT JOIN t.vagoni vag "
				+ "GROUP BY t.id_treno" ;
		
		Query q = manager.createQuery(jpql);
		
		List<TrenoCompletoDTO> l = q.getResultList();
		
		return l;
	}

	@Override
	public List<TrenoCompletoDTO> filtraTrenoCompleto(FiltroDTO filtroDTO, Utente utente) {
	    StringBuilder jpql = new StringBuilder("SELECT new com.corso.dto.TrenoCompletoDTO(t.id_treno, t.sigla, t.id_utente, t.fabbrica, AVG(val.voto), SUM(vag.peso), SUM(vag.prezzo), SUM(vag.lunghezza), SUM(vag.biglietti) ) "
	            + "FROM Treno t "
	            + "LEFT JOIN t.valutazione val "
	            + "LEFT JOIN t.vagoni vag "
	            + "GROUP BY t.id_treno "
	            + "HAVING 1 = 1"); // start with a true condition

	    if (filtroDTO.getPrezzoMin() != null) {
	        jpql.append(" AND SUM(vag.prezzo) >= :prezzoMin");
	    }
	    if (filtroDTO.getPrezzoMax() != null) {
	        jpql.append(" AND SUM(vag.prezzo) <= :prezzoMax");
	    }
	    if (filtroDTO.getLunghezzaMin() != null) {
	        jpql.append(" AND SUM(vag.lunghezza) >= :lunghezzaMin");
	    }
	    if (filtroDTO.getLunghezzaMax() != null) {
	        jpql.append(" AND SUM(vag.lunghezza) <= :lunghezzaMax");
	    }
	    if (filtroDTO.getPesoMin() != null) {
	        jpql.append(" AND SUM(vag.peso) >= :pesoMin");
	    }
	    if (filtroDTO.getPesoMax() != null) {
	        jpql.append(" AND SUM(vag.peso) <= :pesoMax");
	    }
	    if(filtroDTO.getSigla() != null) {
	    	jpql.append(" AND t.sigla LIKE :sigla");
	    }
	    if(utente != null) {
	    	jpql.append(" AND t.id_utente = :utente");
	    }
	    
	    if (filtroDTO.getSortField() != null && filtroDTO.getSortOrder() != null) {
	        jpql.append(" ORDER BY ");
	        switch (filtroDTO.getSortField()) {
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
	        jpql.append(" " + filtroDTO.getSortOrder());
	    }
	    
	    
	    Query q = manager.createQuery(jpql.toString());

	    if (filtroDTO.getPrezzoMin() != null) {
	        q.setParameter("prezzoMin", filtroDTO.getPrezzoMin());
	    }
	    if (filtroDTO.getPrezzoMax() != null) {
	        q.setParameter("prezzoMax", filtroDTO.getPrezzoMax());
	    }
	    if (filtroDTO.getLunghezzaMin() != null) {
	        q.setParameter("lunghezzaMin", filtroDTO.getLunghezzaMin());
	    }
	    if (filtroDTO.getLunghezzaMax() != null) {
	        q.setParameter("lunghezzaMax", filtroDTO.getLunghezzaMax());
	    }
	    if (filtroDTO.getPesoMin() != null) {
	        q.setParameter("pesoMin", filtroDTO.getPesoMin());
	    }
	    if (filtroDTO.getPesoMax() != null) {
	        q.setParameter("pesoMax", filtroDTO.getPesoMax());
	    }
	    if(filtroDTO.getSigla() != null) {
	    	q.setParameter("sigla", "%" + filtroDTO.getSigla() + "%");
	    }
	    if(utente != null) {
	    	q.setParameter("utente", utente);
	    }
	    
	    List<TrenoCompletoDTO> l = q.getResultList();

	    return l;
	}

	
	@Override
	public List<Valutazione> findAllValutazione(){
		Query q = (Query) manager.createQuery("from Valutazione", Valutazione.class);

		List<Valutazione> l = q.getResultList();
		return l;
	}
	
	@Override
	public List<Utente> findByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TrenoCompletoDTO> findByIdUtente(int utenteId) {
		String jpql = "SELECT new com.corso.dto.TrenoCompletoDTO(t.id_treno, t.sigla, t.id_utente, t.fabbrica, AVG(val.voto), SUM(vag.peso), SUM(vag.prezzo), SUM(vag.lunghezza), SUM(vag.biglietti)) "
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
    public TrenoCompletoDTO findTrenoCompletoById(int idTreno) {
        String hql = "SELECT new com.corso.dto.TrenoCompletoDTO(t.id_treno, t.sigla, t.id_utente, t.fabbrica, AVG(val.voto), SUM(vag.peso), SUM(vag.prezzo), SUM(vag.lunghezza), SUM(vag.biglietti)) " 
                    + "FROM Treno t " 
                    + "LEFT JOIN t.valutazione val "
     	            + "LEFT JOIN t.vagoni vag "
                    + "WHERE t.id_treno = :idTreno";

        Query query = manager.createQuery(hql);
        query.setParameter("idTreno", idTreno);

        return (TrenoCompletoDTO) query.getSingleResult();
    }
	
	@Override
	public TrenoCompletoDTO findTrenoCompletoBySigla(String sigla) {
	    String jpql = "SELECT new com.corso.dto.TrenoCompletoDTO(t.id_treno, t.sigla, t.id_utente, t.fabbrica, AVG(val.voto), SUM(vag.peso), SUM(vag.prezzo), SUM(vag.lunghezza), SUM(vag.biglietti)) "
	                + "FROM Treno t "
	                + "LEFT JOIN t.valutazione val "
	                + "LEFT JOIN t.vagoni vag "
	                + "WHERE t.sigla = :sigla "
	                + "GROUP BY t.id_treno";
	    
	    Query query = manager.createQuery(jpql);
	    query.setParameter("sigla", sigla);
	    List<TrenoCompletoDTO> l = query.getResultList();
	    
	    return l.get(0);
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
