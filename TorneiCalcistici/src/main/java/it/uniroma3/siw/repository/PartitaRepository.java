package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Partita;

@Repository
public interface PartitaRepository extends JpaRepository<Partita,Integer>
{

	@Query("""
			select distinct p
			from Partita p
			join fetch p.squadraHome
			join fetch p.squadraAway
			left join fetch p.arbitro
			where p.torneo.id = :id
			order by p.dataOra asc
			""")
			List<Partita> findByTorneoId(@Param("id") int id);
	
	@Query("""
		    SELECT p
		    FROM Partita p
		    WHERE p.stato <> 'FINITA'
		""")
		List<Partita> findPartiteAperte();
	
	@Query("""
			SELECT p FROM Partita p
			LEFT JOIN FETCH p.squadraHome
			LEFT JOIN FETCH p.squadraAway
			LEFT JOIN FETCH p.arbitro
			LEFT JOIN FETCH p.torneo
			""")
			List<Partita> findAllComplete();
	
	void deleteBySquadraHomeId(int id);
	void deleteBySquadraAwayId(int id);
	
}
