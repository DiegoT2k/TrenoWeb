package com.corso.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.corso.dto.TrenoCompleto;
import com.corso.dto.TrenoVoto;
import com.corso.model.Fabbrica;
import com.corso.model.Treno;
import com.corso.model.TrenoFilter;
import com.corso.model.Utente;
import com.corso.model.Valutazione;

@Transactional
public interface TrenoDao {
	 
	public int add(Treno treno);
	
	public Utente find(int id);
	
	public Treno findTreno(int id);
	
	public Fabbrica find(String sigla);
	
	public List<Treno> findAll();
	
	public List<Treno> findByFilter(TrenoFilter filter);
	
	public List<Utente> findByName(String nome);
	
	public List<Object[]> filterByPeso(/**TrenoFilter filter**/);
	
	public List<Valutazione> findAllValutazione();
	
	public List<TrenoCompleto> findTrenoCompleto();
	
	public List<TrenoCompleto> findByIdUtente(int utenteId);
	
	public void updateTreno(Treno treno);
		
	public List<TrenoCompleto> filtraTrenoCompleto(Double prezzoMin, Double prezzoMax, Double lunghezzaMin, 
			Double lunghezzaMax, Double pesoMin, Double pesoMax, String sigla, Utente utente);
	
	public void deleteTreno(int idTreno);
}
