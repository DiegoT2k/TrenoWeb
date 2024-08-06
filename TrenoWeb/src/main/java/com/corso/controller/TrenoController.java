package com.corso.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.corso.dto.TrenoCompleto;
import com.corso.exception.TrenoException;
import com.corso.service.TrenoService;
import com.corso.service.ValutazioneService;
import com.corso.vo.FiltroVO;


@Controller
public class TrenoController {

	@Autowired
	private TrenoService trenoService;
	
	@Autowired
	private ValutazioneService valutazioneService;
	 
	 @GetMapping("/home")
	 public String home(Model model, HttpSession session) {

		 
		 model.addAttribute("username", session.getAttribute("username"));
		 model.addAttribute("message", "Benvenuto nella homepage!");
		
		 System.out.println("Sei nella homepage");
		 return "home";
	 }

	 @GetMapping("/treni")
	 public String treni(Model model) {	 
		 
		 List<TrenoCompleto> l = trenoService.trenoCompleto();
		 model.addAttribute("filtroVO", new FiltroVO());
		 model.addAttribute("listaTreni", l);
		 return "treni";
		 
	 }
	 
	 
	 @GetMapping("/modulo")
	 public String crea(Model model, HttpSession session) {
		 System.out.println("\nSono nella pagina modulo creazione\n");
		 model.addAttribute("id_utente", session.getAttribute("utente"));
		 
		 return "modulo";
	 }
	 
	 
	 @PostMapping("/crea")
	 public String creazioneTreno(@RequestParam String sigla, 
			 					@RequestParam String fabbrica, 
			 					@RequestParam int id_utente,
			 					RedirectAttributes redirectAttributes) {
		 
		 System.out.println("\nSto provando a costruire il treno " + sigla + " e fabbrica " + fabbrica);
		 
		 try {
			trenoService.creaTreno(sigla, fabbrica, id_utente);
		 }catch (TrenoException e) {
			 redirectAttributes.addFlashAttribute("errorMessage", e.getSuggerimento());
	        return "redirect:/modulo";
		 }
		 
		 return "redirect:/treni";
	 }

	 
	 @PostMapping("addVoto")
	 public String votaTreno(@RequestParam int rating, @RequestParam int trenoId, HttpSession session) {
		 
		 System.out.println("voto :" + rating + " trenoid :" + trenoId + " utente " + session.getAttribute("utente") );
		 
		 if(session.getAttribute("utente") != null) {
			 valutazioneService.addVoto(rating, trenoId, (Integer) session.getAttribute("utente"));
		 }
		  
		 return "redirect:/" + trenoId;
	 }
	 

	 @PostMapping("filtro")
	 public String filtraTreni(@ModelAttribute("filtroVO") FiltroVO filtroVO, Model model) {
		 
		 List<TrenoCompleto> l = trenoService.filtraTreno(filtroVO);
		 model.addAttribute("filtroVO", new FiltroVO());
		 model.addAttribute("listaTreni", l);
		
		 return "treni";
	 }
	 
	 @GetMapping("/acquista/{id_treno}")
	 public String acquistaBiglietto(@PathVariable("id_treno") int idTreno, HttpSession session) {
		 System.out.println("acquisto del biglietto del treno " + idTreno);
		 
		 if(session.getAttribute("utente") != null) {
			 trenoService.acquistaBiglietti(idTreno);
		 }
		 
		 return "redirect:/" + idTreno;
	 }
	 
}
