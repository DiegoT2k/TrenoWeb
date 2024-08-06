package com.corso.vo;

public class FiltroVO {
	
	private Double pesoMin;
	private Double pesoMax;
	private Double prezzoMin;
	private Double prezzoMax;
	private Double lunghezzaMin;
	private Double lunghezzaMax;
	private String sigla;
	private String username;
	private String sortField;
	private String sortOrder;
	
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
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
	

	
}
