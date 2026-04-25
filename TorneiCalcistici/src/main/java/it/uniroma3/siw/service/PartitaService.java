package it.uniroma3.siw.service;

import java.util.List;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.repository.PartitaRepository;


@Service
public class PartitaService 
{

	private PartitaRepository repository;
	
	public PartitaService(PartitaRepository repository)
	{
		this.repository = repository;
	}
	
	public Partita findById(int id)
	{
		return repository.findById(id).get();
	}
	
	
	public List<Partita> findAll()
	{
		return (List<Partita>) repository.findAll();
	}
	
	public Partita save(Partita saveMe) 
	{
		return this.repository.save(saveMe);
	}
	
}
