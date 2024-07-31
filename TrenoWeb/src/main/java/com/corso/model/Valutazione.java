package com.corso.model;

import javax.persistence.*;

@Entity
public class Valutazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_utente")
	private Utente id_utente;
	
	@ManyToOne
	@JoinColumn(name="id_treno")
	private Treno id_treno;
	
	@Column(name="voto")
	private int voto;

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}
/**
	public ValutazioneId getId() {
		return id;
	}

	public void setId(ValutazioneId id) {
		this.id = id;
	}
**/
	public Utente getUtente() {
		return id_utente;
	}

	public void setUtente(Utente utente) {
		this.id_utente = utente;
	}

	public Treno getTreno() {
		return id_treno;
	}

	public void setTreno(Treno treno) {
		this.id_treno = treno;
	}
	
}
