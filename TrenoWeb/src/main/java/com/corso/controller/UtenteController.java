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

import com.corso.dto.TrenoCompleto;
import com.corso.model.Treno;
import com.corso.model.Utente;
import com.corso.service.TrenoService;
import com.corso.service.UserService;
import com.corso.vo.TrenoVO;



@Controller
public class UtenteController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TrenoService trenoService;
	
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
            return "redirect:/utente/profilo";
        }
        TrenoVO trenoVO = new TrenoVO();
        trenoVO.setSigla(treno.getSigla);
        model.addAttribute("trenoVO", trenoVO);
        return "modificaTreno";
    }

    // Gestisce l'aggiornamento del treno
    @PostMapping("/modificaTreno")
    public String updateTreno(@Valid @ModelAttribute("trenoVO") TrenoVO trenoVO,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "modificaTreno";
        }
        
        Treno treno = trenoService.findTreno(trenoVO.getIdTreno());
        if (treno == null) {
            return "redirect:/utente/profilo";
        }

        treno.setSigla(trenoVO.getSigla());
        trenoService.updateTreno(treno);
        
        return "redirect:/utente/profilo";
    }
}
