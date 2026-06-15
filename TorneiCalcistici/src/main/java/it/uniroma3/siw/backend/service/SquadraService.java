package it.uniroma3.siw.backend.service;

import java.util.List;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.backend.model.Squadra;
import it.uniroma3.siw.backend.repository.SquadraRepository;


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
		java.util.List<Squadra> list = new java.util.ArrayList<>();
		repository.findAll().forEach(list::add);
		return list;
	}
	
	public Squadra save(Squadra saveMe) 
	{
		return this.repository.save(saveMe);
	}

	public SquadraRepository getRepository() {
		return repository;
	}
	
}
