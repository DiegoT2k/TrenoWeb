package com.corso.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;;

@Controller
public class TrenoController {
	
	 @GetMapping("/index")
	 public String index(){   
	   
		System.out.println("sono passato dal metodo controller index()!"); 	
		
      return "index";  
	}  	
	 
	 @GetMapping("/home")
	 public String home() {
		 System.out.println("Sei nella homepage");
		 
		 return "home";
	 }

}
