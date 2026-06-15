package it.uniroma3.siw.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.backend.model.Giocatore;
import it.uniroma3.siw.backend.model.Squadra;

public interface SquadraRepository extends JpaRepository<Squadra,Integer>
{
    @Query("SELECT g FROM Giocatore g WHERE g.squadraId = :id")
    List<Giocatore> findAllGiocatori(@Param("id") int id);
}
