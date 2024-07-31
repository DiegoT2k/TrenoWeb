package com.corso.model.abs_vagone;

import javax.persistence.*;


import com.corso.model.Fabbrica;
import com.corso.model.Tipologia;
import com.corso.model.Treno;

@Entity
@Table(name="vagone")
public abstract class Vagone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id_vagone;
	
	@ManyToOne
	@JoinColumn(name="fabbrica")
	protected Fabbrica fabbrica;
	
	@Column(name="prezzo")
	protected double prezzo;
	
	@Column(name="lunghezza")
	protected double lunghezza;
	
	@Column(name="peso")
	protected double peso;
	
	@ManyToOne
	@JoinColumn(name="id_treno")
	protected Treno id_treno;
	
	@ManyToOne
	@JoinColumn(name="tipo")
	protected Tipologia tipo;

	public int getId_vagone() {
		return id_vagone;
	}
	
	public void setId_vagone(int id_vagone) {
		this.id_vagone = id_vagone;
	}

	public Fabbrica getFabbrica() {
		return fabbrica;
	}

	public void setFabbrica(Fabbrica fabbrica) {
		this.fabbrica = fabbrica;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public double getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(double lunghezza) {
		this.lunghezza = lunghezza;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Treno getId_treno() {
		return id_treno;
	}

	public void setId_treno(Treno id_treno) {
		this.id_treno = id_treno;
	}

	public Tipologia getTipo() {
		return tipo;
	}

	public void setTipo(Tipologia tipo) {
		this.tipo = tipo;
	}

}
