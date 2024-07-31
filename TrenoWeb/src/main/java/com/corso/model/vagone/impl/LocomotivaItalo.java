package com.corso.model.vagone.impl;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import com.corso.model.Fabbrica;
import com.corso.model.Tipologia;
import com.corso.model.Treno;
import com.corso.model.vagone.LocomotivaAbs;

@Entity
public class LocomotivaItalo extends LocomotivaAbs{

	@Override
	public String toString() {
		return "LocomotivaItalo [id_vagone=" + id_vagone + ", fabbrica=" + fabbrica + ", prezzo=" + prezzo
				+ ", lunghezza=" + lunghezza + ", peso=" + peso + ", id_treno=" + id_treno + ", tipo=" + tipo + "]";
	}
	
}
