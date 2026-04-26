package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.*;

@Controller
public class UtenteController 
{
	private final TorneoRepository torneoRepository;
	private final SquadraRepository squadraRepository;
	private final GiocatoreRepository giocatoreRepository;
	private final PartitaRepository partitaRepository;
	private final ArbitroRepository arbitroRepository;
	
	public UtenteController 
		(
			TorneoRepository torneoRepository,
			SquadraRepository squadraRepository,
			GiocatoreRepository giocatoreRepository,
			PartitaRepository partitaRepository,
			ArbitroRepository arbitroRepository
		)
	{
		this.torneoRepository = torneoRepository;
		this.squadraRepository = squadraRepository;
		this.giocatoreRepository = giocatoreRepository;
		this.partitaRepository = partitaRepository;
		this.arbitroRepository = arbitroRepository;
	}

	@GetMapping("/form/admin")
	public String adminPage ()
	{
		return "form/admin";
	}
	
	// TORNEO //////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/torneoForm")
	public String torneoForm(Model model)
	{
		model.addAttribute("torneo", new Torneo());
		return "form/torneoForm";
	}
	
	@PostMapping("/tornei")
	public String torneoNew(@ModelAttribute("torneo") Torneo torneo)
	{
		this.torneoRepository.save(torneo);
		return "redirect:/tornei";
	}
	
	// SQUADTRA //////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/squadraForm")
	public String squadraForm(Model model)
	{
		model.addAttribute("squadra", new Squadra());
		return "form/squadraForm";
	}
	
	@PostMapping("/squadre")
	public String squadraNew(@ModelAttribute("squadra") Squadra squadra)
	{
		this.squadraRepository.save(squadra);
	    return "redirect:/squadre/" + squadra.getId();	
	    }
	
	// GIOCATORE //////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/giocatoreForm")
	public String giocatoreForm (Model model)
	{
		model.addAttribute("giocatore", new Giocatore());
		model.addAttribute("squadre", this.squadraRepository.findAll());
		return "form/giocatoreForm";
	}
	
	@PostMapping("/giocatori")
	public String giocatoreNew(@ModelAttribute("giocatore") Giocatore giocatore)
	{
		this.giocatoreRepository.save(giocatore);
		return "redirect:/squadre/" + giocatore.getsquadraId();
	}
	
	// PARTITA //////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/partitaForm")
	public String partitaForm(Model model)
	{
		model.addAttribute("partita", new Partita());
		
		model.addAttribute("torneo", this.torneoRepository.findAll());
		model.addAttribute("squadra", this.squadraRepository.findAll());
		model.addAttribute("arbitro", this.arbitroRepository.findAll());
		
		return "form/partitaForm";
	}
	
	@PostMapping("/partite")
	public String partitaNew(@ModelAttribute("partita") Partita partita)
	{
		this.partitaRepository.save(partita);
		
		return "redirect:/tornei/" + partita.getTorneo().getId() + "/calendario";
	}
	
}
