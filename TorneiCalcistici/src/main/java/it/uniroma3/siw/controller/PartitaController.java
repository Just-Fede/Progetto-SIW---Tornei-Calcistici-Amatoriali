package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.repository.*;

@Controller
public class PartitaController 
{
	private final PartecipazioneRepository partecipazioneRepository;
	private final PartitaRepository partitaRepository;
	private final TorneoRepository torneoRepository;
	private final SquadraRepository squadraRepository;
	private final ArbitroRepository arbitroRepository;
	
	public PartitaController
		(
			PartitaRepository partitaRepository,
			TorneoRepository torneoRepository,
			SquadraRepository squadraRepository,
			ArbitroRepository arbitroRepository, PartecipazioneRepository partecipazioneRepository
		) 
	{
		this.partitaRepository = partitaRepository;
		this.torneoRepository = torneoRepository;
		this.squadraRepository = squadraRepository;
		this.arbitroRepository = arbitroRepository;
		this.partecipazioneRepository = partecipazioneRepository;
	}
	
	// PARTITA //////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/partitaForm")
	public String partitaForm(Model model)
	{
		model.addAttribute("partita", new Partita());
		
		model.addAttribute("torneo", this.torneoRepository.findAll());
		model.addAttribute("squadra", this.squadraRepository.findAll());
		model.addAttribute("arbitro", this.arbitroRepository.findAll());
		
		return "/admin/partite/partitaForm";
	}
	
	@PostMapping("/partite")
	public String partitaNew(@ModelAttribute("partita") Partita partita)
	{
		this.partitaRepository.save(partita);
		
		return "redirect:/tornei/" + partita.getTorneo().getId() + "/calendario";
	}
	
	@GetMapping("/partiteListModifica")
	public String listaPartite(Model model) {

	    model.addAttribute("partite", partitaRepository.findPartiteAperte());

	    return "/admin/partite/partiteListModifica";
	}
	
	@GetMapping("partitaModifica/{id}")
	public String partitaModifica(@PathVariable Integer id, Model model) {

	    Partita partita = partitaRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Partita non trovata"));

	    model.addAttribute("partita", partita);

	    return "/admin/partite/partitaModifica";
	}
	
	@PostMapping("/partitaModifica/{id}")
	public String salvaRisultato(@PathVariable Integer id,
	                             @ModelAttribute Partita partitaForm) {

	    Partita partita = partitaRepository.findById(id)
	        .orElseThrow();

	    partita.setGoalsHome(partitaForm.getGoalsHome());
	    partita.setGoalsAway(partitaForm.getGoalsAway());
	    partita.setStato(partitaForm.getStato());

	    partitaRepository.save(partita);

	    return "redirect:/tornei/" + partita.getTorneo().getId();
	}
	
	@GetMapping("/partitaListElimina")
	public String partiteListElimina(Model model)
	{
		model.addAttribute("partite",this.partitaRepository.findAllComplete());
		return "/admin/partite/partitaListElimina";
	}
	
	@PostMapping("/partitaElimina/{id}")
	public String partitaElimina(@PathVariable Integer id)
	{
		this.partitaRepository.deleteById(id);
		return "redirect:/partitaListElimina";
	}
}
