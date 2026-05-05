package it.uniroma3.siw.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredenzialiService;
import it.uniroma3.siw.service.UtenteService;


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

        if (!role.equals("USER") && !role.equals("ROLE_ADMIN")) {
            role = "USER";
        }

        Utente u = new Utente();
        u.setUsername(username);
        u = utenteService.save(u);

        this.credenzialiService.register(username, password, role);

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
		return "/home";
	}
}
