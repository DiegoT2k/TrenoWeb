package com.corso.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;


import com.corso.model.Utente;

import com.corso.config.Beans;
import com.corso.dao.TrenoDao;
import com.corso.model.Treno;
import com.corso.model.builder.TrenoBuilder;
import com.corso.model.builder.impl.TrenoItaloBuilder;
import com.corso.model.builder.impl.TrenoTrenordBuilder;
import com.corso.service.TrenoService;
import com.corso.service.UserService;
import com.corso.service.impl.TrenoServiceImpl;

import com.corso.service.impl.UserServiceImpl;
import com.corso.vo.LoginVO;
import com.corso.vo.RegistrationVO;;

@Controller
public class TrenoController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private TrenoService trenoService;


	 @GetMapping("/registration")
	 public String preRegistration(Model model) {
		 model.addAttribute("registrationVO", new RegistrationVO());
		 return "registration";
	 }
	 
	 @PostMapping("postRegistrazione")
	 public String postRegistrazione(@ModelAttribute("registrationVO") RegistrationVO registrationVO, Model model) {
	 
		 Utente utente = new Utente();
		 BeanUtils.copyProperties(registrationVO, utente);
		 
		 userService.save(utente);
		 
		 return "redirect:/login";
	 }
	 
	 @GetMapping("/home")
	 public String home(@RequestParam String username, Model model) {
		 
		 System.out.println("Sei nella homepage");
		 model.addAttribute("username", username);
		 
		 return "home";
	 }
	 
	 @GetMapping("/login")
	 public String preLogin(Model model) {
		 
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
			 model.addAttribute("error", "Username non trovato");
		 }
		 else {
			 if (utente.getPassword().equals(loginVO.getPassword())) {
				 session.setAttribute("utente", utente); //aggiunge la sessione
				 return "home";
				 
			 } else {
				model.addAttribute("error", "Password errata");
				return "login";
			 }
		 }
			 
		 
		 System.out.println("username " + loginVO.getUsername() + " password " + loginVO.getPassword());
	
		 //ritorna al login se non riesce a farlo
		 return "login";
	 }

	 @GetMapping("/treni")
	 public String treni(Model model) {
		
		 List<Treno> listaTreni = trenoService.allTreni();
		 System.out.println("Homepage dei treni");
		 model.addAttribute("listaTreni", listaTreni);
		 System.out.println("Lista " + listaTreni);
		 
		 return "treni";
		 
	 }
	 
	 @GetMapping("/modulo")
	 public String crea() {
		 System.out.println("\nSono nella pagina modulo creazione\n");
		 return "modulo";
	 }
	 
	 @PostMapping("/crea")
	 public String creazioneTreno(@RequestParam String sigla, @RequestParam String fabbrica) {
		 System.out.println("\nSto provando a costruire il treno " + sigla + " e fabbrica " + fabbrica);
		 trenoService.creaTreno(sigla, fabbrica, 1);
		 return "redirect:/treni";
	 }

	 
}
