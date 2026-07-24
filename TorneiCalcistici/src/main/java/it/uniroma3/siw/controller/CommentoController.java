package it.uniroma3.siw.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.PartitaRepository;
import it.uniroma3.siw.repository.UtenteRepository;
import it.uniroma3.siw.service.CommentoService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CommentoController {

    private final CommentoService commentoService;
    private final PartitaRepository partitaRepository;
    private final UtenteRepository utenteRepository;

    public CommentoController(CommentoService commentoService, PartitaRepository partitaRepository, UtenteRepository utenteRepository) {
        this.partitaRepository = partitaRepository;
        this.commentoService = commentoService;
        this.utenteRepository = utenteRepository;
    }

    @GetMapping("/commenti/{id}")
    public String show(@PathVariable("id") int id, Model model) 
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean loggato = auth != null && auth.isAuthenticated()
                && !(auth instanceof org.springframework.security.authentication.AnonymousAuthenticationToken);

        Partita p = partitaRepository.findById(id).orElse(null);
        model.addAttribute("partita", p);
        model.addAttribute("utenteLoggato", loggato ? auth.getName() : null);

        return "/commentiList";
    }

    @GetMapping("/commentoNew/{pid}")
    public String addCommento(@PathVariable("pid") int pid, Model model) {
        model.addAttribute("pid", pid);
        return "/utenti/commentoNew";
    }

    @PostMapping("commentoNew/{pid}")
    public String salvaCommento(
            @PathVariable int pid,
            @RequestParam String testo
    ) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // oppure email

        // recupera utente dal DB
        Utente u = utenteRepository.findByUsername(username);

        Commento c = new Commento();
        c.setTesto(testo);
        c.setPartita(partitaRepository.findById(pid).orElse(null));
        c.setUtente(u);

        commentoService.save(c);

        return "redirect:/commenti" + "/" + pid;
    }

    @GetMapping("/commentoModifica/{id}")
    public String modificaCommento(@PathVariable Integer id, Model model) {

        Commento commento = commentoService.findById(id);

        model.addAttribute("commento", commento);

        return "utenti/commentoModifica";
    }

    @PostMapping("/commentoModifica/{id}")
    public String salvaModificaCommento(
            @PathVariable Integer id,
            @RequestParam String testo) {

        Commento c = commentoService.findById(id);

        c.setTesto(testo);

        commentoService.save(c);

        return "redirect:/commenti/" + c.getPartita().getId();
    }
}
