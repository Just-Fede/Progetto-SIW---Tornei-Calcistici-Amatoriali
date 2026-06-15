package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.repository.CommentoRepository;

@Service
public class CommentoService 
{
    private final CommentoRepository repository;

    public CommentoService(CommentoRepository repository)
    {
        this.repository = repository;
    }

    public Commento findById(int id)
    {
        return repository.findById(id).get();
    }

    public List<Commento> findAll()
    {
        return (List<Commento>) repository.findAll();
    }

    public Commento save(Commento commento)
    {
        return repository.save(commento);
    }
}
