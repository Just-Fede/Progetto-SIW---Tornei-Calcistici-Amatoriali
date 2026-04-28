package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Partita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private LocalDateTime dataOra;

	private String luogo;

	@Min(0)
	private int goalsHome;

	@Min(0)
	private int goalsAway;

	private String stato;

	@ManyToOne
	@JoinColumn(name = "torneo_id")
	private Torneo torneo;

	@ManyToOne
	@JoinColumn(name = "squadra_home_id")
	private Squadra squadraHome;

	@ManyToOne
	@JoinColumn(name = "squadra_away_id")
	private Squadra squadraAway;

	@ManyToOne
	@JoinColumn(name = "arbitro_id")
	private Arbitro arbitro;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDataOra() {
		return dataOra;
	}

	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public int getGoalsHome() {
		return goalsHome;
	}

	public void setGoalsHome(int goalsHome) {
		this.goalsHome = goalsHome;
	}

	public int getGoalsAway() {
		return goalsAway;
	}

	public void setGoalsAway(int goalsAway) {
		this.goalsAway = goalsAway;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Torneo getTorneo() {
		return torneo;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

	public Squadra getSquadraHome() {
		return squadraHome;
	}

	public void setSquadraHome(Squadra squadraHome) {
		this.squadraHome = squadraHome;
	}

	public Squadra getSquadraAway() {
		return squadraAway;
	}

	public void setSquadraAway(Squadra squadraAway) {
		this.squadraAway = squadraAway;
	}

	public Arbitro getArbitro() {
		return arbitro;
	}

	public void setArbitro(Arbitro arbitro) {
		this.arbitro = arbitro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partita other = (Partita) obj;
		return id == other.id;
	}




	
}

