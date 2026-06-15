package it.uniroma3.siw.backend.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.backend.model.Giocatore;

public interface GiocatoreRepository extends CrudRepository<Giocatore,Integer>
{
	void deleteBySquadraId(int id);
}
