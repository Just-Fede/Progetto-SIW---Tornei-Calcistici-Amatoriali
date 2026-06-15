package it.uniroma3.siw.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.backend.model.Torneo;

public interface TorneoRepository extends JpaRepository<Torneo,Integer>
{

@Query("""
    SELECT 
        s.id,
        s.nome,

        SUM(
            CASE
                WHEN (p.squadraHome = s AND p.goalsHome > p.goalsAway) THEN 3
                WHEN (p.squadraAway = s AND p.goalsAway > p.goalsHome) THEN 3
                WHEN p.goalsHome = p.goalsAway THEN 1
                ELSE 0
            END
        ),

        COUNT(p.id),

        SUM(
            CASE
                WHEN p.squadraHome = s THEN p.goalsHome
                WHEN p.squadraAway = s THEN p.goalsAway
                ELSE 0
            END
        ),

        SUM(
            CASE
                WHEN p.squadraHome = s THEN p.goalsAway
                WHEN p.squadraAway = s THEN p.goalsHome
                ELSE 0
            END
        ),

        SUM(
            CASE
                WHEN p.squadraHome = s THEN (p.goalsHome - p.goalsAway)
                WHEN p.squadraAway = s THEN (p.goalsAway - p.goalsHome)
                ELSE 0
            END
        )

    FROM Squadra s
    JOIN Partita p ON (p.squadraHome = s OR p.squadraAway = s)

    WHERE p.torneo.id = :id
      AND p.stato = 'FINITA'

    GROUP BY s.id, s.nome
    ORDER BY 3 DESC, 7 DESC, 5 DESC
    """)
List<Object[]> getClassifica(@Param("id") Integer id);
	
@Query("""
    SELECT t
    FROM Torneo t
    LEFT JOIN FETCH t.partite
    WHERE t.id = :id
""")
Torneo findJoinFetch(@Param("id") int id);

@EntityGraph(attributePaths = {
    "partite",
    "partite.squadraHome",
    "partite.squadraAway",
    "partite.arbitro"
})
@Query("SELECT t FROM Torneo t WHERE t.id = :id")
Torneo findEntityGraph(@Param("id") int id);

@Query("""
SELECT t FROM Torneo t
LEFT JOIN FETCH t.partecipazioni p
LEFT JOIN FETCH p.squadra
WHERE t.id = :id
""")
Torneo findFullById(@Param("id") int id);

}
