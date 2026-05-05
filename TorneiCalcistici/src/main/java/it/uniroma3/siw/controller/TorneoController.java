package it.uniroma3.siw.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.service.TorneoService;
import it.uniroma3.siw.repository.PartecipazioneRepository;
import it.uniroma3.siw.repository.PartitaRepository;
import it.uniroma3.siw.repository.TorneoRepository;

@Controller
public class TorneoController {

    private final TorneoService torneoService;
    private final TorneoRepository torneoRepository;
    private final PartecipazioneRepository partecipazioneRepository;
    private final PartitaRepository partitaRepository;
    
    public TorneoController(TorneoService service,
    		PartecipazioneRepository partecipazioneRepository,
    		PartitaRepository partitaRepository) 
    {
        this.torneoService = service;
        this.torneoRepository = service.getRepository();
        this.partecipazioneRepository = partecipazioneRepository;
        this.partitaRepository = partitaRepository;
    }

    @GetMapping("/tornei/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("torneo", this.torneoService.findById(id));

        model.addAttribute("classifica", this.torneoRepository.getClassifica(id));
        
        model.addAttribute("squadre",this.partecipazioneRepository.findSquadreByTorneo(id));
        
        return "/tornei/show";
    }

    @GetMapping("/tornei/{id}/calendario")
    public String calendar(@PathVariable("id") int id, Model model)
    {
        Set<Partita> partite = new LinkedHashSet<>(
            this.partitaRepository.findByTorneoId(id));

        model.addAttribute("partite", partite);
        return "tornei/calendar";
    }
    
    @GetMapping("/tornei")
    public String list(Model model) {
        List<Torneo> allTornei = this.torneoService.findAll();
        model.addAttribute("tornei", allTornei);
        return "tornei/list";
    }
	
	@GetMapping("tornei/torneoForm")
	public String torneoForm(Model model)
	{
		model.addAttribute("torneo", new Torneo());
		return "/admin/tornei/torneoForm";
	}
	
	@PostMapping("/tornei")
	public String torneoNew(@ModelAttribute("torneo") Torneo torneo)
	{
		this.torneoRepository.save(torneo);
		return "redirect:/tornei";
	}
	
	@GetMapping("/torneoListModifica")
	public String torneoListModifica(Model model)
	{
		List<Torneo> allTornei = this.torneoService.findAll();
		model.addAttribute("tornei", allTornei);
		return "/admin/tornei/torneoListModifica";
	}
	
	@GetMapping("/torneoModifica/{id}")
	public String torneoModifica(@PathVariable("id") int id, Model model)
	{
		model.addAttribute("torneo", this.torneoService.findById(id));
		return("/admin/tornei/torneoModifica");
	}
	
	@PostMapping("/torneoSalvaModifica")
	public String tornepSalvaModifica(@ModelAttribute Torneo torneo)
	{
		this.torneoService.save(torneo);
		return "redirect:/admin/tornei/torneoListModifica";
	}
    
}