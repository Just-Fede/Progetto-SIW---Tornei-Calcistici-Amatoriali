package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.service.TorneoService;
import it.uniroma3.siw.repository.PartecipazioneRepository;

@Controller
public class TorneoController {

    private final TorneoService service;
    private final PartecipazioneRepository partecipazioneRepository;

    public TorneoController(TorneoService service,
                            PartecipazioneRepository partecipazioneRepository) {
        this.service = service;
        this.partecipazioneRepository = partecipazioneRepository;
    }

    @GetMapping("/tornei/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("torneo", this.service.findById(id));

        model.addAttribute("squadre",
                this.partecipazioneRepository.findSquadreByTorneo(id));

        return "tornei/show";
    }

    @GetMapping("/tornei")
    public String list(Model model) {
        List<Torneo> allTornei = this.service.findAll();
        model.addAttribute("tornei", allTornei);
        return "tornei/list";
    }
}