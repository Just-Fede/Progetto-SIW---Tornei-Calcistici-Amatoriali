package it.uniroma3.siw.backend.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.backend.dto.ClassificaRowDTO;
import it.uniroma3.siw.backend.dto.PartitaDTO;
import it.uniroma3.siw.backend.dto.TorneoDTO;
import it.uniroma3.siw.backend.dto.PartecipazioneDTO;
import it.uniroma3.siw.backend.dto.SquadraDTO;


import it.uniroma3.siw.backend.model.Torneo;
import it.uniroma3.siw.backend.service.TorneoService;

@RestController
@RequestMapping("/api/tornei")
@CrossOrigin(origins = "http://localhost:5173")
public class TorneoApiController {

    private final TorneoService service;

    public TorneoApiController(TorneoService service) {
        this.service = service;
    }

@GetMapping
public List<TorneoDTO> getAll() {
    return service.findAll().stream()
        .map(t -> {

            List<PartecipazioneDTO> partecipazioni =
                t.getPartecipazioni().stream()
                    .map(p -> new PartecipazioneDTO(
                        p.getId(),
                        new SquadraDTO(
                            p.getSquadra().getId(),
                            p.getSquadra().getNome()
                        )
                    ))
                    .toList();

            return new TorneoDTO(
                t.getId(),
                t.getNome(),
                t.getAnno(),
                t.getDescrizione(),
                partecipazioni
            );
        })
        .toList();
}

@GetMapping("/{id}")
public ResponseEntity<TorneoDTO> getById(@PathVariable int id) {

    Torneo t = service.findById(id);

    List<PartecipazioneDTO> partecipazioni =
        t.getPartecipazioni().stream()
            .map(p -> new PartecipazioneDTO(
                p.getId(),
                new SquadraDTO(
                    p.getSquadra().getId(),
                    p.getSquadra().getNome()
                )
            ))
            .toList();

    return ResponseEntity.ok(
        new TorneoDTO(
            t.getId(),
            t.getNome(),
            t.getAnno(),
            t.getDescrizione(),
            partecipazioni
        )
    );
}

    @GetMapping("/{id}/classifica")
    public List<ClassificaRowDTO> classifica(@PathVariable("id") int id) {
        return service.getClassifica(id);
    }

    @GetMapping("/{id}/calendario")
    public List<PartitaDTO> calendario(@PathVariable("id") int id) {
        return service.getCalendario(id);
    }
}
