package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.repository.GiocatoreRepository;
import it.uniroma3.siw.repository.SquadraRepository;

@Controller
public class GiocatoreController 
{

	private final GiocatoreRepository giocatoreRepository;
	private final SquadraRepository squadraRepository;
	
	public GiocatoreController(GiocatoreRepository giocatoreRepository, SquadraRepository squadraRepository)
	{
		this.giocatoreRepository = giocatoreRepository;
		this.squadraRepository = squadraRepository;
	}
	
	
	// GIOCATORE //////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("giocatori/giocatoreForm")
	public String giocatoreForm (Model model)
	{
		model.addAttribute("giocatore", new Giocatore());
		model.addAttribute("squadre", this.squadraRepository.findAll());
		return "giocatori/giocatoreForm";
	}
	
	@PostMapping("giocatori")
	public String giocatoreNew(@ModelAttribute("giocatore") Giocatore giocatore)
	{
		this.giocatoreRepository.save(giocatore);
		return "redirect:/squadre/" + giocatore.getsquadraId();
	}
}
