package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Partecipazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Squadra squadra;

    @ManyToOne
    private Torneo torneo;

    public int getId() {
        return id;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Partecipazione other = (Partecipazione) obj;
        return id == other.id;
    }
}