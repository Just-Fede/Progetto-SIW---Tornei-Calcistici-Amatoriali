package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class Partita {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank
	private LocalDateTime data_ora;
	
	@NotBlank
	private String luogo;
	
	// Chiavi esterne
	
	@NotBlank
	private int goals_home;
	
	@NotBlank
	private int goals_away;
	
	@NotBlank
	private int arbitro_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getData_ora() {
		return data_ora;
	}

	public void setData_ora(LocalDateTime data_ora) {
		this.data_ora = data_ora;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public int getGoals_home() {
		return goals_home;
	}

	public void setGoals_home(int goals_home) {
		this.goals_home = goals_home;
	}

	public int getGoals_away() {
		return goals_away;
	}

	public void setGoals_away(int goals_away) {
		this.goals_away = goals_away;
	}

	public int getArbitro_id() {
		return arbitro_id;
	}

	public void setArbitro_id(int arbitro_id) {
		this.arbitro_id = arbitro_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(arbitro_id, data_ora, goals_away, goals_home, id, luogo);
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
		return arbitro_id == other.arbitro_id && Objects.equals(data_ora, other.data_ora)
				&& goals_away == other.goals_away && goals_home == other.goals_home && id == other.id
				&& Objects.equals(luogo, other.luogo);
	}
	
	
}
