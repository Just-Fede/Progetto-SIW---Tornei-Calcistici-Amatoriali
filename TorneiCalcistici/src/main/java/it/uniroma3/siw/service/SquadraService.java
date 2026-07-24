package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.repository.PartecipazioneRepository;
import it.uniroma3.siw.repository.SquadraRepository;


 @Service
public class SquadraService 
{

	private final SquadraRepository repository;
	private final PartecipazioneRepository partecipazioneRepository;
	
	public SquadraService(SquadraRepository repository,PartecipazioneRepository partecipazioneRepository)
	{
		this.repository = repository;
		this.partecipazioneRepository = partecipazioneRepository;
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

	public List<Giocatore> findAllGiocatori(int id) 
	{
		return this.repository.findAllGiocatori(id);
	}

	public void deleteById(int id) 
	{
		// nel service, prima di eliminare la squadra
		partecipazioneRepository.deleteAll(partecipazioneRepository.findBySquadraId(id));
		repository.deleteById(id);
		
	}
	
}
