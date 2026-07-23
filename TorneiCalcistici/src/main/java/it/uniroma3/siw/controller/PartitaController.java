package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.service.*;

@Controller
public class PartitaController 
{
	private final PartitaService partitaService;
	private final TorneoService torneoService;
	private final SquadraService squadraService;
	private final ArbitroService arbitroService;
	
	public PartitaController
		(
			PartitaService partitaService,
			TorneoService torneoService,
			SquadraService squadraService,
			ArbitroService arbitroService
		) 
	{
		this.partitaService = partitaService;
		this.torneoService = torneoService;
		this.squadraService = squadraService;
		this.arbitroService = arbitroService;
	}
	
	// PARTITA //////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/partitaForm")
	public String partitaForm(Model model)
	{
		model.addAttribute("partita", new Partita());
		
		model.addAttribute("torneo", this.torneoService.findAll());
		model.addAttribute("squadra", this.squadraService.findAll());
		model.addAttribute("arbitro", this.arbitroService.findAll());
		
		return "/admin/partite/partitaForm";
	}
	
	@PostMapping("/partite")
	public String partitaNew(@ModelAttribute("partita") Partita partita)
	{
		this.partitaService.save(partita);
		
		return "redirect:/tornei";
	}
	
	@GetMapping("/partiteListModifica")
	public String listaPartite(Model model)
	{
		model.addAttribute("partite", this.partitaService.findPartiteAperte());
		return "/admin/partite/partiteListModifica";
	}
	
	@GetMapping("partitaModifica/{id}")
	public String partitaModifica(@PathVariable Integer id, Model model)
	{
		Partita partita = this.partitaService.findById(id);
		model.addAttribute("partita", partita);
		return "/admin/partite/partitaModifica";
	}
	
	@PostMapping("/partitaModifica/{id}")
	public String salvaRisultato(@PathVariable Integer id, @ModelAttribute Partita partitaForm)
	{
		Partita partita = this.partitaService.findById(id);
		partita.setGoalsHome(partitaForm.getGoalsHome());
		partita.setGoalsAway(partitaForm.getGoalsAway());
		partita.setStato(partitaForm.getStato());
		this.partitaService.save(partita);
		return "redirect:/tornei";
	}
	
	@GetMapping("/partitaListElimina")
	public String partiteListElimina(Model model)
	{
		model.addAttribute("partite", this.partitaService.findAllComplete());
		return "/admin/partite/partitaListElimina";
	}
	
	@PostMapping("/partitaElimina/{id}")
	public String partitaElimina(@PathVariable Integer id)
	{
		this.partitaService.deleteById(id);
		return "redirect:/partitaListElimina";
	}
}