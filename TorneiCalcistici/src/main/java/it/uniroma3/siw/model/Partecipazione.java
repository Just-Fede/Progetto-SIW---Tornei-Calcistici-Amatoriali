package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public class Partecipazione {

	@NotBlank
	private int squadra_id;
	
	@NotBlank
	private int torneo_id;

	public int getSquadra_id() {
		return squadra_id;
	}

	public void setSquadra_id(int squadra_id) {
		this.squadra_id = squadra_id;
	}

	public int getTorneo_id() {
		return torneo_id;
	}

	public void setTorneo_id(int torneo_id) {
		this.torneo_id = torneo_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(squadra_id, torneo_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partecipazione other = (Partecipazione) obj;
		return squadra_id == other.squadra_id && torneo_id == other.torneo_id;
	}
	
}
