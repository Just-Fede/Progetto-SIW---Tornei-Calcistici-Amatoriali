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
			""")
	
	List<Partita> findByTorneoId(@Param("id") int id);
	
}
