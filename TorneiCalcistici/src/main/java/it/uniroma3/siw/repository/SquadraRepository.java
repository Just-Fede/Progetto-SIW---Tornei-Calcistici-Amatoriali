package it.uniroma3.siw.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;
import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.model.Squadra;

@Repository
public interface SquadraRepository extends JpaRepository<Squadra,Integer>
{
	@Query("SELECT g FROM Giocatore g WHERE g.squadraId = :id")
	List<Giocatore> findAllGiocatori(@Param("id") int id);
}
