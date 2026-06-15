package it.uniroma3.siw.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import it.uniroma3.siw.backend.model.Utente;
import it.uniroma3.siw.backend.repository.UtenteRepository;

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
