package com.corso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.corso.dao.TrenoDao;
import com.corso.dto.TrenoCompleto;
import com.corso.dto.TrenoVoto;
import com.corso.model.Treno;
import com.corso.model.abs_vagone.Vagone;
import com.corso.vo.FiltroVO;

public interface TrenoService {
	
	public void creaTreno(String sigla, String fabbrica, int id_utente);
	
	public void checkStringa(String vagoni);
	
	public List<Treno> allTreni();
	
	public List<TrenoCompleto> trenoCompleto();
	
	public List<TrenoCompleto> findByIdUtente(int utenteId);
	
	public Treno findTreno(int idTreno);
	
	public void updateTreno(Treno treno);
	
	public List<TrenoCompleto> filtraTreno(FiltroVO filtroVO);
	
	void deleteTreno(int idTreno);
	
	public void updateTrenoSigla(Treno treno, String newSigla);
	
	public void deleteVagoniByTreno(Treno treno);
	
	public void recreateVagoni(Treno treno);
	
	public TrenoCompleto findTrenoCompletoById(int idTreno);
	
	public void acquistaBiglietti(int idTreno) throws Exception;
}
