package com.corso.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.corso.dto.TrenoCompleto;
import com.corso.service.TrenoService;
import com.corso.service.UserService;
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
			 					@RequestParam int id_utente) {
		 System.out.println("\nSto provando a costruire il treno " + sigla + " e fabbrica " + fabbrica);
		 trenoService.creaTreno(sigla, fabbrica, id_utente);
		 return "redirect:/treni";
	 }

	 
	 @PostMapping("addVoto")
	 public String votaTreno(@RequestParam int rating, @RequestParam int trenoId, HttpSession session) {
		 
		 System.out.println("voto :" + rating + " trenoid :" + trenoId + " utente "+session.getAttribute("utente") );
		 
		 valutazioneService.addVoto(rating, trenoId, (Integer) session.getAttribute("utente"));
		 
		 return "redirect:/treni";
	 }
	 

	 @PostMapping("filtro")
	 public String filtraTreni(@ModelAttribute("filtroVO") FiltroVO filtroVO, Model model) {
		 
		 List<TrenoCompleto> l = trenoService.filtraTreno(filtroVO);
		 model.addAttribute("filtroVO", new FiltroVO());
		 model.addAttribute("listaTreni", l);
		
		 return "treni";
	 }
	 
}
