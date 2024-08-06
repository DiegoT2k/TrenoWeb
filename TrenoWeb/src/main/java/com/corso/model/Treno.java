package com.corso.model;

import com.corso.model.abs_vagone.Vagone;

import java.util.Set;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Treno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_treno;
	
	@ManyToOne
	@JoinColumn(name="id_utente")
	private Utente id_utente;
	
	@ManyToOne
	@JoinColumn(name="fabbrica")
	private Fabbrica fabbrica;
	
	@Column(name="sigla")
	private String sigla;
	
	@OneToMany(
			mappedBy = "id_treno",
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private Set<Vagone> vagoni;
	
	@OneToMany(
			mappedBy = "id_treno",
			fetch = FetchType.EAGER
			)
	private Set<Valutazione> valutazione;
	
	public Set<Valutazione> getValutazioni() {
		return valutazione;
	}

	public Set<Vagone> getVagoni() {
		return vagoni;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
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
	
	@Override
	public String toString() {
		return "Treno [id_treno=" + id_treno + ", id_utente=" + id_utente.getUsername() + ", " + fabbrica + "]";
	}
	
	
	
}
