package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class Giocatore {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cognome;
	
	@NotBlank
	private LocalDate data_nascita;
	
	@NotBlank
	private String ruolo;
	
	@NotBlank
	private double altezza;
	
	@NotBlank
	private int squadra_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(LocalDate data_nascita) {
		this.data_nascita = data_nascita;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public double getAltezza() {
		return altezza;
	}

	public void setAltezza(double altezza) {
		this.altezza = altezza;
	}

	public int getSquadra_id() {
		return squadra_id;
	}

	public void setSquadra_id(int squadra_id) {
		this.squadra_id = squadra_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(altezza, cognome, data_nascita, id, nome, ruolo, squadra_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Giocatore other = (Giocatore) obj;
		return Double.doubleToLongBits(altezza) == Double.doubleToLongBits(other.altezza)
				&& Objects.equals(cognome, other.cognome) && Objects.equals(data_nascita, other.data_nascita)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(ruolo, other.ruolo) && squadra_id == other.squadra_id;
	}
	
}
