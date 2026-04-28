package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UtenteController 
{

	@GetMapping("/admin")
	public String adminPage ()
	{
		return "/admin";
	}
	
}
