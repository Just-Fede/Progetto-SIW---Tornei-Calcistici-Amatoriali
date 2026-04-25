package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.SquadraService;

@Controller
public class SquadraController 
{
	
	private final SquadraService squadraService;
	
	public SquadraController(SquadraService squadraService)
	{
		this.squadraService = squadraService;
	}
	
	@GetMapping("/squadre/{id}")
	public String show (@PathVariable("id") int id, Model model)
	{
		model.addAttribute("squadra",this.squadraService.findById(id));
		
		model.addAttribute("giocatori", this.squadraService.getRepository().findAllGiocatori(id));
		return "squadre/show";
	}
	
}
