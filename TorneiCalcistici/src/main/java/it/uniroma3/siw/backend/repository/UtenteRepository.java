package it.uniroma3.siw.backend.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.backend.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente,Integer>
{
    public Utente findByUsername(String username);
    
}
