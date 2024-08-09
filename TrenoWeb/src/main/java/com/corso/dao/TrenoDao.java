package com.corso.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.repository.query.Param;
import com.corso.dto.FiltroDTO;
import com.corso.dto.TrenoCompletoDTO;
import com.corso.model.Fabbrica;
import com.corso.model.Treno;
import com.corso.model.Utente;
import com.corso.model.Valutazione;


@Transactional
public interface TrenoDao {
	 
	public int add(Treno treno);
	
	public Utente find(int id);
	
	public Treno findTreno(int id);
	
	public Fabbrica find(String sigla);
	
	public List<Treno> findAll();
	
	public List<Utente> findByName(String nome);
	
	public List<Valutazione> findAllValutazione();
	
	public List<TrenoCompletoDTO> findTrenoCompleto();
	
	public List<TrenoCompletoDTO> findByIdUtente(int utenteId);
	
	public void updateTreno(Treno treno);
		
	public List<TrenoCompletoDTO> filtraTrenoCompleto(FiltroDTO filtroDTO, Utente utente);
	
	public void deleteTreno(int idTreno);
	
	public void deleteVagoniByTreno(Treno treno);
	
	public TrenoCompletoDTO findTrenoCompletoById(@Param("idTreno") int idTreno);
	
	public void decrementaBiglietti(Treno idTreno) throws Exception;

	public TrenoCompletoDTO findTrenoCompletoBySigla(String sigla);

}
