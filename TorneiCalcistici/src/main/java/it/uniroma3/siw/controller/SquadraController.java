package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.service.SquadraService;
import it.uniroma3.siw.repository.GiocatoreRepository;
import it.uniroma3.siw.repository.PartitaRepository;
import it.uniroma3.siw.repository.SquadraRepository;

@Controller
public class SquadraController 
{
	
	private final SquadraService squadraService;
	private final SquadraRepository squadraRepository;
	private final PartitaRepository partitaRepository;
	private final GiocatoreRepository giocatoreRepository;
	
	public SquadraController
	(
			SquadraService squadraService,
			SquadraRepository squadraRepository,
			GiocatoreRepository giocatoreRepository,
			PartitaRepository partitaRepository
			)
	{
		this.squadraService = squadraService;
		this.squadraRepository = squadraRepository;
		this.giocatoreRepository = giocatoreRepository;
		this.partitaRepository = partitaRepository;
	}
	
	@GetMapping("/squadre/{id}")
	public String show (@PathVariable("id") int id, Model model)
	{
		model.addAttribute("squadra",this.squadraService.findById(id));
		
		model.addAttribute("giocatori", this.squadraService.getRepository().findAllGiocatori(id));
		return "squadre/show";
	}
	
	// SQUADRA //////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/squadraForm")
	public String squadraForm(Model model)
	{
		model.addAttribute("squadra", new Squadra());
		return "admin/squadre/squadraForm";
	}
	
	@PostMapping("/squadre")
	public String squadraNew(@ModelAttribute("squadra") Squadra squadra)
	{
		this.squadraRepository.save(squadra);
	    return "redirect:/squadre/" + squadra.getId();	
	    }
	
	@GetMapping("/squadraListModifica")
	public String squadraList (Model model)
	{
		model.addAttribute("squadre", this.squadraRepository.findAll());
		return("/admin/squadre/squadraListModifica");
	}
	
	@GetMapping("/squadraModifica/{id}")
	public String squadraModifica (@PathVariable("id") int id, Model model)
	{
		model.addAttribute("squadra", this.squadraRepository.findById(id));
		return"/admin/squadre/squadraModifica";
	}
	
	@PostMapping("/squadraModifica")
	public String squadraSalvaModifica (@ModelAttribute("squadra") Squadra squadra)
	{
		this.squadraRepository.save(squadra);
		return"redirect:/squadraListModifica";
	}
	
	@PostMapping("/squadraElimina/{id}")
	@Transactional
	public String elimina(@PathVariable int id)
	{
	    giocatoreRepository.deleteBySquadraId(id);

	    partitaRepository.deleteBySquadraHomeId(id);
	    partitaRepository.deleteBySquadraAwayId(id);
	    
	    this.squadraRepository.deleteById(id);
	    return "redirect:/squadraListModifica";
	}
	
}
