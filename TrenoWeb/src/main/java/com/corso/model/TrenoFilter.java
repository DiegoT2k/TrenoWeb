package com.corso.model;

public class TrenoFilter {

	int id_utente;
	
	private String nomeUtente = null;
	
	private Integer bigliettiMin = null;
	private Integer bigliettiMax = null;
	
	private Double prezzoMin = null;
	private Double prezzoMax = null;
	
	private Double lunghezzaMin = null;
	private Double lunghezzaMax = null;
	
	private Double pesoMin = null;
	private Double pesoMax = null;
	

	public int getId_utente() {
		return id_utente;
	}

	public void setId_utente(int id_utente) {
		this.id_utente = id_utente;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public Integer getBigliettiMin() {
		return bigliettiMin;
	}

	public void setBigliettiMin(Integer bigliettiMin) {
		this.bigliettiMin = bigliettiMin;
	}

	public Integer getBigliettiMax() {
		return bigliettiMax;
	}

	public void setBigliettiMax(Integer bigliettiMax) {
		this.bigliettiMax = bigliettiMax;
	}

	public Double getPrezzoMin() {
		return prezzoMin;
	}

	public void setPrezzoMin(Double prezzoMin) {
		this.prezzoMin = prezzoMin;
	}

	public Double getPrezzoMax() {
		return prezzoMax;
	}

	public void setPrezzoMax(Double prezzoMax) {
		this.prezzoMax = prezzoMax;
	}

	public Double getLunghezzaMin() {
		return lunghezzaMin;
	}

	public void setLunghezzaMin(Double lunghezzaMin) {
		this.lunghezzaMin = lunghezzaMin;
	}

	public Double getLunghezzaMax() {
		return lunghezzaMax;
	}

	public void setLunghezzaMax(Double lunghezzaMax) {
		this.lunghezzaMax = lunghezzaMax;
	}

	public Double getPesoMin() {
		return pesoMin;
	}

	public void setPesoMin(Double pesoMin) {
		this.pesoMin = pesoMin;
	}

	public Double getPesoMax() {
		return pesoMax;
	}

	public void setPesoMax(Double pesoMax) {
		this.pesoMax = pesoMax;
	}
	
	
	
}
