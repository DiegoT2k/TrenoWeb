package com.corso.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.corso.model.Utente;
import com.corso.dto.TrenoCompleto;
import com.corso.dto.TrenoVoto;
import com.corso.service.TrenoService;
import com.corso.service.UserService;
import com.corso.service.ValutazioneService;
import com.corso.vo.LoginVO;
import com.corso.vo.RegistrationVO;;

@Controller
public class TrenoController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private TrenoService trenoService;
	
	@Autowired
	private ValutazioneService valutazioneService;


	 @GetMapping("/registration")
	 public String preRegistration(Model model) {
		 model.addAttribute("registrationVO", new RegistrationVO());
     return "registration";
	 }
	 
	 @PostMapping("postRegistrazione")
	 public String postRegistrazione(@Valid @ModelAttribute("registrationVO") 
	 RegistrationVO registrationVO, BindingResult bindingResult, Model model) {
		 
		// Verifica se ci sono errori di validazione
		if (bindingResult.hasErrors()) {
			 return "registration";
		}
		 
		// Verifica se l'username � gi� in uso
	    if (!userService.isUsernameUnique(registrationVO.getUsername())) {
	        bindingResult.rejectValue("username", "", "Username gi� in uso");
	    }

	    // Verifica se l'email � gi� in uso
	    if (!userService.isEmailUnique(registrationVO.getEmail())) {
	        bindingResult.rejectValue("email", "", "Email gi� in uso");
	    }

	    // Se ci sono errori, ritorna alla pagina di registrazione
	    if (bindingResult.hasErrors()) {
	        return "registration";
	    }
		 
		 Utente utente = new Utente();
		 BeanUtils.copyProperties(registrationVO, utente);
		 
		 userService.save(utente);
		 
		 return "redirect:/login";
	 }
	 
	 
	 @GetMapping("/home")
	 public String home(Model model, HttpSession session) {

		 
		 model.addAttribute("username", session.getAttribute("username"));
		 model.addAttribute("message", "Benvenuto nella homepage!");
		
		 System.out.println("Sei nella homepage");
		 return "home";
	 }
	 
	 @GetMapping("/login")
	 public String preLogin(Model model, HttpSession session) {
		 
		 if(session.getAttribute("utente") != null) {
			 return "redirect:/home";
		 }
		 
		 model.addAttribute(new LoginVO());
		 
		 return "login";
	 }
	 
	 @PostMapping("postLogin")
	 public String postLogin(@Valid @ModelAttribute("loginVO") LoginVO loginVO,
			 		BindingResult bindingResult, Model model, HttpSession session) {
		
		 if (bindingResult.hasErrors()) {
			 return "login";
		 }

		 Utente utente = userService.checkLogin(loginVO.getUsername());
		 
		 if(utente == null) {
			 model.addAttribute("error_username", "Username non trovato");
		 }
		 else {
			 if (utente.getPassword().equals(loginVO.getPassword())) {
				 session.setAttribute("utente", utente.getId_utente());
				 session.setAttribute("username", utente.getUsername());
				 return "redirect:/home";
				 
			 } else {
				model.addAttribute("error_password", "Password errata");
				return "login";
			 }
		 }

		 System.out.println("username " + loginVO.getUsername() + " password " + loginVO.getPassword());

		 return "login";
	 }

	 
	 
	 @GetMapping("/treni")
	 public String treni(Model model) {	 
		 
		 List<TrenoVoto> l = trenoService.votoTreni();
	
		 model.addAttribute("listaTreni", l);
		 return "treni";
		 
	 }
	 

	 @GetMapping("/treni2")
	 public String treni2(Model model) {	 
		 
		 List<TrenoCompleto> l = trenoService.trenoCompleto();
	
		 model.addAttribute("listaTreni", l);
		 return "treni2";
		 
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
	 public String votaTreno(@RequestParam int rating, @RequestParam int trenoId) {
		 
		 valutazioneService.addVoto(rating, trenoId, 56);
		 
		 return "redirect:/treni";
	 }
	 
	 @GetMapping("/logout")
	 public String logout(HttpSession session) {
		 session.invalidate();
		 return "redirect:/login";
	 }
	 
}
