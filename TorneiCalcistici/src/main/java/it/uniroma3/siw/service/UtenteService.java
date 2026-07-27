package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;

@Service
public class UtenteService {

	private final UtenteRepository repository;
	
	public UtenteService(UtenteRepository repository)
	{
		this.repository = repository;
	}
	
	public Utente findById(int id)
	{
		return repository.findById(id).get();
	}
	
	public Utente findByUsername(String username)
	{
		return repository.findByUsername(username);
	}
	
	
	public List<Utente> findAll()
	{
		java.util.List<Utente> list = new java.util.ArrayList<>();
		repository.findAll().forEach(list::add);
		return list;
	}
	
	public Utente save(Utente saveMe) 
	{
		return this.repository.save(saveMe);
	}
}
