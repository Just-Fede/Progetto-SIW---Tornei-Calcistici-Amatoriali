package it.uniroma3.siw.service;

import java.util.List;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.repository.TorneoRepository;

@Service
public class TorneoService 
{

	private TorneoRepository repository;
	
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
		return (List<Torneo>) repository.findAll();
	}
	
	public Torneo save(Torneo saveMe) 
	{
		return this.repository.save(saveMe);
	}

	public TorneoRepository getRepository() {
		return repository;
	}
	
	
}
