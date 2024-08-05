package com.corso.dto;

import com.corso.model.Fabbrica;
import com.corso.model.Utente;

public class TrenoCompleto {

	private int id_treno;
	private Utente id_utente;
	private Fabbrica fabbrica;
	private Double voto;
	private Double peso;
	private Double prezzo;
	private Double lunghezza;
	
	public TrenoCompleto(int id_treno, Utente id_utente, Fabbrica fabbrica, Double voto, Double peso, Double prezzo,
			Double lunghezza) {
		this.id_treno = id_treno;
		this.id_utente = id_utente;
		this.fabbrica = fabbrica;
		this.voto = voto;
		this.peso = peso;
		this.prezzo = prezzo;
		this.lunghezza = lunghezza;
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

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public Double getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(Double lunghezza) {
		this.lunghezza = lunghezza;
	}
	
}
