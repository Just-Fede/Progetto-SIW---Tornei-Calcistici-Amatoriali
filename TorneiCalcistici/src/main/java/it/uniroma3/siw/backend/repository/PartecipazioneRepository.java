package it.uniroma3.siw.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.backend.model.Partecipazione;
import it.uniroma3.siw.backend.model.Squadra;

public interface PartecipazioneRepository
	extends JpaRepository<Partecipazione, Integer> {

    @Query("""
	    SELECT DISTINCT s
	    FROM Partecipazione p
	    JOIN p.squadra s
	    WHERE p.torneo.id = :id
	""")
	List<Squadra> findSquadreByTorneo(@Param("id") Integer id);
}