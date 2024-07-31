package com.corso.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrenoController {
	
	@GetMapping("/index")
	 public String index(){   
	   
		System.out.println("sono passato dal metodo controller index()!"); 	
		
      return "index";  
  }  	
	
}
