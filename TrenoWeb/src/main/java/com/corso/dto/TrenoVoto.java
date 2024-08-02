package com.corso.dto;

import com.corso.model.Fabbrica;
import com.corso.model.Treno;
import com.corso.model.Utente;

public class TrenoVoto {

	private int id_treno;
	private Utente id_utente;
	private Fabbrica fabbrica;
	private Double voto;
	public TrenoVoto(int id_treno, Utente id_utente, Fabbrica fabbrica, Double voto) {
		this.id_treno = id_treno;
		this.id_utente = id_utente;
		this.fabbrica = fabbrica;
		this.voto = voto;
	}
	public int getId_treno() {
		return id_treno;
	}
	public void setId_treno(int id_treno) {
		this.id_treno = id_treno;
	}
	public Utente getId_utente() {
		return id_utente;
	}
	public void setId_utente(Utente id_utente) {
		this.id_utente = id_utente;
	}
	public Fabbrica getFabbrica() {
		return fabbrica;
	}
	public void setFabbrica(Fabbrica fabbrica) {
		this.fabbrica = fabbrica;
	}
	public Double getVoto() {
		return voto;
	}
	public void setVoto(Double voto) {
		this.voto = voto;
	}
	
}
