package it.uniroma3.siw.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.backend.model.Commento;
import it.uniroma3.siw.backend.repository.CommentoRepository;

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
        java.util.List<Commento> list = new java.util.ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    public Commento save(Commento commento)
    {
        return repository.save(commento);
    }
}
