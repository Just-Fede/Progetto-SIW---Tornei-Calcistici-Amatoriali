package it.uniroma3.siw.service;

import it.uniroma3.siw.repository.UtenteRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Utente;

@Service
public class UtenteService {

	private UtenteRepository repository;
	
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
		return (List<Utente>) repository.findAll();
	}
	
	public Utente save(Utente saveMe) 
	{
		return this.repository.save(saveMe);
	}
}
