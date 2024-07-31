package com.corso.model;

import javax.persistence.*;

@Entity
public class Fabbrica implements Bean{

	@Id
	private String sigla;
	
	@Column(name="fabbrica")
	private String fabbrica;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getFabbrica() {
		return fabbrica;
	}

	public void setFabbrica(String fabbrica) {
		this.fabbrica = fabbrica;
	}
	
}
