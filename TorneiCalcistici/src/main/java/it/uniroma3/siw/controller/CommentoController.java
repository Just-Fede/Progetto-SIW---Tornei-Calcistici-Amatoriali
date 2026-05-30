package it.uniroma3.siw.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;
import it.uniroma3.siw.service.CommentoService;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import it.uniroma3.siw.repository.PartitaRepository;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String show(@PathVariable("id") int id, Model model) {

        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();

        Partita p = partitaRepository.findById(id).orElse(null);

        model.addAttribute("commenti", p.getCommenti());
        model.addAttribute("pid", p.getId());
        model.addAttribute("utenteLoggato", auth.getName());

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
