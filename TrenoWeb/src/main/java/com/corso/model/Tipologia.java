package com.corso.model;

import javax.persistence.*;

@Entity
public class Tipologia implements Bean {

	@Id
	private String tipo;

	public Tipologia() {
		super();
	}

	public Tipologia(String tipo) {
		super();
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
