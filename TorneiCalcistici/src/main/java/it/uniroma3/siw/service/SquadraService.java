package it.uniroma3.siw.service;

import java.util.List;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.repository.SquadraRepository;


 @Service
public class SquadraService 
{

	private SquadraRepository repository;
	
	public SquadraService(SquadraRepository repository)
	{
		this.repository = repository;
	}
	
	public Squadra findById(int id)
	{
		return repository.findById(id).get();
	}
	
	
	public List<Squadra> findAll()
	{
		return (List<Squadra>) repository.findAll();
	}
	
	public Squadra save(Squadra saveMe) 
	{
		return this.repository.save(saveMe);
	}

	public SquadraRepository getRepository() {
		return repository;
	}
	
}
