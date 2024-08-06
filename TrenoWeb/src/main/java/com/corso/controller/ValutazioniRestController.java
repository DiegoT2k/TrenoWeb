package com.corso.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corso.model.Utente;
import com.corso.model.Valutazione;
import com.corso.service.ValutazioneService;

@RestController
public class ValutazioniRestController {

	@Autowired
	private ValutazioneService valutazioneService;

	@GetMapping(path="/rest/valutazioni/getAll",  produces = {"application/json"})
	public List<Valutazione> getAllValutazioni() {
		return valutazioneService.getAll();
	}

	//Da aggiustare poi se si vuole vedere in base all'id utente/voto
//	@GetMapping(path="rest/valutazioni/{id}", produces = {"application/json"})
//	public Valutazione getById(@PathVariable int id) {
//		return valutazioneService.findById(id);
//	}

//	@GetMapping(path="rest/valutazioni/{id_utente}", produces = {"application/json"})
//	public Utente getByIdUtente(@PathVariable int id) {
//		return valutazioneService.findUtente(id);
//	}
	 

	 
	
}
