package it.uniroma3.siw.service;

import java.util.List;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.repository.GiocatoreRepository;

@Service
public class GiocatoreService 
{
	private GiocatoreRepository repository;
	
	public GiocatoreService(GiocatoreRepository repository)
	{
		this.repository = repository;
	}
	
	public Giocatore findById(int id)
	{
		return repository.findById(id).get();
	}
	
	
	public List<Giocatore> findAll()
	{
		return (List<Giocatore>) repository.findAll();
	}
	
	public Giocatore save(Giocatore saveMe) 
	{
		return this.repository.save(saveMe);
	}
}
