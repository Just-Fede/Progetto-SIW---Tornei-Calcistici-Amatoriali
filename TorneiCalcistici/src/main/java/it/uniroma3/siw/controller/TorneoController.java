package it.uniroma3.siw.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.service.TorneoService;
import it.uniroma3.siw.repository.PartecipazioneRepository;
import it.uniroma3.siw.repository.PartitaRepository;
import it.uniroma3.siw.repository.TorneoRepository;

@Controller
public class TorneoController {

    private final TorneoService service;
    private final TorneoRepository torneoRepository;
    private final PartecipazioneRepository partecipazioneRepository;
    private final PartitaRepository partitaRepository;
    
    public TorneoController(TorneoService service,
    		PartecipazioneRepository partecipazioneRepository,
    		PartitaRepository partitaRepository) 
    {
        this.service = service;
        this.torneoRepository = service.getRepository();
        this.partecipazioneRepository = partecipazioneRepository;
        this.partitaRepository = partitaRepository;
    }

    @GetMapping("/tornei/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("torneo", this.service.findById(id));

        model.addAttribute("classifica", this.torneoRepository.getClassifica(id));
        
        model.addAttribute("squadre",this.partecipazioneRepository.findSquadreByTorneo(id));
        
        return "tornei/show";
    }

    @GetMapping("/tornei/{id}/calendario")
    public String calendar(@PathVariable("id") int id, Model model)
    {
        Set<Partita> partite = new LinkedHashSet<>(
            this.partitaRepository.findByTorneoId(id)
        );

        model.addAttribute("partite", partite);
        return "tornei/calendar";
    }
    
    @GetMapping("/tornei")
    public String list(Model model) {
        List<Torneo> allTornei = this.service.findAll();
        model.addAttribute("tornei", allTornei);
        return "tornei/list";
    }
    
}