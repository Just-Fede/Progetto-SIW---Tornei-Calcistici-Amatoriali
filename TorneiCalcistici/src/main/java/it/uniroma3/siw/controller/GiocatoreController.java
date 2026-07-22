package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.GiocatoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.repository.GiocatoreRepository;
import it.uniroma3.siw.repository.SquadraRepository;

@Controller
public class GiocatoreController 
{

	private final GiocatoreService giocatoreService;
	private final GiocatoreRepository giocatoreRepository;
	private final SquadraRepository squadraRepository;
	
	public GiocatoreController(GiocatoreRepository giocatoreRepository, SquadraRepository squadraRepository, GiocatoreService giocatoreService)
	{
		this.giocatoreRepository = giocatoreRepository;
		this.squadraRepository = squadraRepository;
		this.giocatoreService = giocatoreService;
	}
	
	
	// GIOCATORE //////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/giocatoreForm")
	public String giocatoreForm (Model model)
	{
		model.addAttribute("giocatore", new Giocatore());
		model.addAttribute("squadre", this.squadraRepository.findAll());
		return "admin/giocatori/giocatoreForm";
	}
	
	@PostMapping("giocatori")
	public String giocatoreNew(@ModelAttribute("giocatore") Giocatore giocatore)
	{
		this.giocatoreRepository.save(giocatore);
		return "redirect:/squadre/" + giocatore.getsquadraId();
	}
	
	@GetMapping("/giocatoreListModifica")
	public String giocatoreList(Model model)
	{
		model.addAttribute("giocatori",this.giocatoreRepository.findAll());
		return"admin/giocatori/giocatoreListModifica";
	}
	
	@GetMapping("/giocatoreModifica/{id}")
	public String giocatoreModifica(@PathVariable("id") int id, Model model)
	{
		model.addAttribute("giocatore", this.giocatoreRepository.findById(id));
		return "/admin/giocatori/giocatoreModifica";
	}
	
	@PostMapping("giocatoreSalvaModifica")
	public String giocatoreSalva (@ModelAttribute Giocatore giocatore)
	{
		this.giocatoreService.save(giocatore);
		return "redirect:/giocatoreListModifica";
	}
}
