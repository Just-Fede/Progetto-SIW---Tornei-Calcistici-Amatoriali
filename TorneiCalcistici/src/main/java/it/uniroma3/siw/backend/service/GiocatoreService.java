package it.uniroma3.siw.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.backend.model.Giocatore;
import it.uniroma3.siw.backend.repository.GiocatoreRepository;

@Service
public class GiocatoreService 
{
	private final GiocatoreRepository repository;
	
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
		java.util.List<Giocatore> list = new java.util.ArrayList<>();
		repository.findAll().forEach(list::add);
		return list;
	}
	
	public Giocatore save(Giocatore saveMe) 
	{
		return this.repository.save(saveMe);
	}
}
