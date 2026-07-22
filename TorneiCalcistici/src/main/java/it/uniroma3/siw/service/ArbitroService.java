package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Arbitro;
import it.uniroma3.siw.repository.ArbitroRepository;

@Service
public class ArbitroService {
	
	private final ArbitroRepository repository;

	public ArbitroService(ArbitroRepository repository)
	{
		this.repository = repository;
	}
	
	public Arbitro findById(int id)
	{
		return repository.findById(id).get();
	}
	
	
	public List<Arbitro> findAll()
	{
		java.util.List<Arbitro> list = new java.util.ArrayList<>();
		repository.findAll().forEach(list::add);
		return list;
	}
	
	public Arbitro save(Arbitro utente) 
	{
		return this.repository.save(utente);
	}
	
}
