package it.uniroma3.siw.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.backend.model.Commento;

@Repository
public interface CommentoRepository extends CrudRepository<Commento,Integer>
{

}
