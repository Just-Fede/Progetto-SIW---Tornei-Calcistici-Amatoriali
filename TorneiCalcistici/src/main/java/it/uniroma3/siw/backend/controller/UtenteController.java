package it.uniroma3.siw.backend.controller;

import static it.uniroma3.siw.backend.SecurityConfiguration.ADMIN_ROLE;
import static it.uniroma3.siw.backend.SecurityConfiguration.DEFAULT_ROLE;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.backend.model.Utente;
import it.uniroma3.siw.backend.service.CredenzialiService;
import it.uniroma3.siw.backend.service.UtenteService;

@Controller
public class UtenteController 
{

	private final CredenzialiService credenzialiService;
	private final UtenteService utenteService;
	
	public UtenteController (CredenzialiService credenzialiService, UtenteService utenteService)
	{
		this.credenzialiService = credenzialiService;
		this.utenteService = utenteService;
	}
	

    @GetMapping("/")
    public String index(Authentication authentication)  
    {
	    if (authentication != null && authentication.isAuthenticated()) 
	        return "redirect:/home";
	    
        return "index";
    }
	
	@GetMapping("/login")
	public String login(Authentication authentication) 
	{
	    if (authentication != null && authentication.isAuthenticated()) 
	        return "redirect:/home";
	    return "login";
	}
	
    
    @GetMapping("/register")
    public String register(Authentication authentication) 
    {
    	if (authentication != null && authentication.isAuthenticated()) 
    		return "redirect:/home";
    	
        return "register";
    }
    
@PostMapping("/register")
public String registrazione(
        @RequestParam String username,
        @RequestParam String password,
        @RequestParam String role
) {

    if (!role.equals(ADMIN_ROLE)) 
	{
		System.out.println("Ruolo " + role + " non valido, assegnato ruolo di default");
        role = DEFAULT_ROLE;
    }

    Utente u = new Utente();
    u.setUsername(username);
    utenteService.save(u);

    credenzialiService.register(username, password, role);

    return "redirect:/login";
}
    
	@GetMapping("/admin")
	public String adminPage ()
	{
		return "/admin/admin";
	}
	
	@GetMapping("/home")
	public String successo(Authentication auth, Model model)
	{
	   model.addAttribute("auth", auth);
		return "/utenti/home";
	}
}
