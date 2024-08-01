package com.corso.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;

import com.corso.config.Beans;
import com.corso.dao.TrenoDao;
import com.corso.model.Treno;
import com.corso.model.builder.TrenoBuilder;
import com.corso.model.builder.impl.TrenoItaloBuilder;
import com.corso.model.builder.impl.TrenoTrenordBuilder;
import com.corso.service.impl.TrenoServiceImpl;
import com.corso.service.impl.UserServiceImpl;;

@Controller
public class TrenoController {
	
	//@Autowired
	private TrenoServiceImpl trenoService = new TrenoServiceImpl();;

	 @GetMapping("/login")
	 public String index(){   
	   
		System.out.println("sono passato dal metodo controller index()!"); 	
		
      return "login";  
	}  	
	 
	 @GetMapping("/home")
	 public String home(@RequestParam String username, Model model) {
		 
		 System.out.println("Sei nella homepage");
		 model.addAttribute("username", username);
		 
		 return "home";
	 }

	 @GetMapping("/treni")
	 public String treni(Model model) {
		
		 List<Treno> listaTreni = trenoService.allTreni();
		 System.out.println("Homepage dei treni");
		 model.addAttribute("listaTreni", listaTreni);
		 System.out.println("questa è la lista " + listaTreni);
		 
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
