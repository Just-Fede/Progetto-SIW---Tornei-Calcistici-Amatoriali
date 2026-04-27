package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UtenteController 
{

	@GetMapping("/form/admin")
	public String adminPage ()
	{
		return "form/admin";
	}
	
}
