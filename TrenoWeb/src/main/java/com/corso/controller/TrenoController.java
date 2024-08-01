package com.corso.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;

import com.corso.model.Utente;
import com.corso.service.impl.UserServiceImpl;;

@Controller
public class TrenoController {
	
//	private UserServiceImpl userservice = new UserServiceImpl();
	
	 @GetMapping("/login")
	 public String index(){   
	   
		System.out.println("sono passato dal metodo controller index()!"); 	
		
      return "login";  
	}  	
	 
	 @GetMapping("/registration")
	 public String registration(Model model) {
		 model.addAttribute("utente", new Utente());
		 return "registration";
	 }
	 
//	 @GetMapping("/checkRegistrazione")
//	 public String checkRegistrazione(@RequestParam String nome, 
//			 							@RequestParam String cognome, 
//			 							@RequestParam String username, 
//			 							@RequestParam String email, 
//			 							@RequestParam String password, 
//			 							Model model) {
//		 Utente utente = new Utente();
//		 utente.setNome(nome);
//		 utente.setCognome(cognome);
//		 utente.setUsername(username);
//		 utente.setEmail(email);
//		 utente.setPassword(password);
//		 userservice.save(utente);
//		 return "login";
//	 }
	 
	 @GetMapping("/home")
	 public String home(@RequestParam String username, Model model) {
		 
		 System.out.println("Sei nella homepage");
		 model.addAttribute("username", username);
		 
		 return "home";
	 }

}
