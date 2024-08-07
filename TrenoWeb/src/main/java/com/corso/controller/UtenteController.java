package com.corso.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.corso.dto.TrenoCompleto;
import com.corso.exception.TrenoException;
import com.corso.model.Treno;
import com.corso.model.Utente;
import com.corso.service.TrenoService;
import com.corso.service.UserService;
import com.corso.vo.LoginVO;
import com.corso.vo.RegistrationVO;
import com.corso.vo.TrenoVO;


@Controller
public class UtenteController {
	
	 @Autowired
	 private UserService userService;
	
	 @Autowired
	 private TrenoService trenoService;
	
	
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
		 
		// Verifica se l'username Ã¯Â¿Â½ giÃ¯Â¿Â½ in uso
	    if (!userService.isUsernameUnique(registrationVO.getUsername())) {

	        bindingResult.rejectValue("username", "", "Username già in uso");


	    }

	    // Verifica se l'email è già in uso
	    if (!userService.isEmailUnique(registrationVO.getEmail())) {
        
	        bindingResult.rejectValue("email", "", "Email già in uso");


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
	 

	 @GetMapping("/logout")
	 public String logout(HttpSession session) {
		 session.invalidate();
		 return "redirect:/login";
	 }
	 
	 
	@GetMapping("/profilo")
    public String mostraProfilo(HttpSession session, Model model) {
		Integer utenteId = (Integer) session.getAttribute("utente");
        if (utenteId == null) {
            return "redirect:/login";
        }

        Utente utente = userService.findById(utenteId);
        if (utente == null) {
            return "redirect:/login";
        }

        List<TrenoCompleto> treniCreati = trenoService.findByIdUtente(utenteId);

        model.addAttribute("utente", utente);
        model.addAttribute("treniCreati", treniCreati);

        return "profilo";
    }
	
	// Visualizza il modulo di modifica del treno
    @GetMapping("/modificaTreno/{id_treno}")
    public String showEditForm(@PathVariable("id_treno") int idTreno, Model model) {
        Treno treno = trenoService.findTreno(idTreno);
        if (treno == null) {
            return "redirect:/profilo";
        }
        TrenoVO trenoVO = new TrenoVO();
        trenoVO.setSigla(treno.getSigla());
        trenoVO.setIdTreno(idTreno);
        model.addAttribute("trenoVO", trenoVO);
        return "modificaTreno";
    }

    // Gestisce l'aggiornamento del treno
    @PostMapping("/modificaTreno/{id_treno}")
    public String updateTreno(@PathVariable("id_treno") int idTreno, @Valid @ModelAttribute("trenoVO") TrenoVO trenoVO,
                              BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "modificaTreno";
        }
        
        Treno treno = trenoService.findTreno(trenoVO.getIdTreno());
        if (treno == null) {
            return "redirect:/profilo";
        }
        
        try {
        	trenoService.updateTrenoSigla(treno, trenoVO.getSigla());
        }catch(TrenoException e){
        	redirectAttributes.addFlashAttribute("errorMessage", e.getSuggerimento());
        	redirectAttributes.addFlashAttribute("trenoVO", trenoVO);
            return "redirect:/modificaTreno/" + idTreno;
        }
        return "redirect:/profilo";
    }
    
    @PostMapping("/gestisciTreno")
    public String gestisciTreno(@RequestParam("idTreno") int idTreno, @RequestParam("azione") String azione) {
        if ("elimina".equals(azione)) {
            trenoService.deleteTreno(idTreno);
        }
        return "redirect:/profilo";
    }
    
    @GetMapping("/{idTreno}")
    public String showTrenoDetails(@PathVariable("idTreno") int idTreno, Model model) {
    	
    	TrenoCompleto trenoCompleto = trenoService.findTrenoCompletoById(idTreno);
    	 
        if (trenoCompleto == null) {
            return "redirect:/treni";
        }

        model.addAttribute("trenoCompleto", trenoCompleto);

        return "trenoDetails";
    }
    

}
