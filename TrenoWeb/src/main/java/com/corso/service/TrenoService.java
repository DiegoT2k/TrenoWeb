package com.corso.service;

import java.util.List;
import javax.transaction.Transactional;
import com.corso.dto.TrenoCompletoDTO;
import com.corso.model.Treno;
import com.corso.vo.FiltroVO;

public interface TrenoService {
	
	public void creaTreno(String sigla, String fabbrica, int id_utente);
	
	public void checkStringa(String vagoni);
	
	public List<Treno> allTreni();
	
	public List<TrenoCompletoDTO> trenoCompleto();
	
	public List<TrenoCompletoDTO> findByIdUtente(int utenteId);
	
	public Treno findTreno(int idTreno);
	
	public void updateTreno(Treno treno);
	
	public List<TrenoCompletoDTO> filtraTreno(FiltroVO filtroVO);
	
	void deleteTreno(int idTreno);
	
	public void updateTrenoSigla(Treno treno, String newSigla);
	
	public void deleteVagoniByTreno(Treno treno);
	
	public void recreateVagoni(Treno treno);
	
	public TrenoCompletoDTO findTrenoCompletoById(int idTreno);

	public void acquistaBiglietti(int idTreno) throws Exception;

	public Treno duplicateTreno(String sigla);
		
	public String invertiSigla(String siglaTreno);


}
