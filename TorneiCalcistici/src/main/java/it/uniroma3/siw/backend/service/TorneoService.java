package it.uniroma3.siw.backend.service;

import java.util.List;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.backend.model.Torneo;
import it.uniroma3.siw.backend.repository.TorneoRepository;

@Service
public class TorneoService 
{

	private final TorneoRepository repository;
	
	public TorneoService(TorneoRepository repository)
	{
		this.repository = repository;
	}
	
	public Torneo findById(int id)
	{
		return repository.findById(id).get();
	}
	
	
	public List<Torneo> findAll()
	{
		java.util.List<Torneo> list = new java.util.ArrayList<>();
		repository.findAll().forEach(list::add);
		return list;
	}
	
	public Torneo save(Torneo saveMe) 
	{
		return this.repository.save(saveMe);
	}

	public TorneoRepository getRepository() {
		return repository;
	}
	
	
}
