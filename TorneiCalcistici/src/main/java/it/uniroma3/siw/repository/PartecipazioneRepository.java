package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.*;
import java.util.*;

@Repository
public interface PartecipazioneRepository
        extends JpaRepository<Partecipazione, Integer> {

    @Query("""
        SELECT s
        FROM Partecipazione p
        JOIN p.squadra s
        WHERE p.torneo.id = :id
    """)
    List<Squadra> findSquadreByTorneo(@Param("id") Integer id);
}