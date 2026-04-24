package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class Arbitro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codice_arbitrale;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cognome;

	public int getCodice_arbitrale() {
		return codice_arbitrale;
	}

	public void setCodice_arbitrale(int codice_arbitrale) {
		this.codice_arbitrale = codice_arbitrale;
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

	@Override
	public int hashCode() {
		return Objects.hash(codice_arbitrale, cognome, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arbitro other = (Arbitro) obj;
		return codice_arbitrale == other.codice_arbitrale && Objects.equals(cognome, other.cognome)
				&& Objects.equals(nome, other.nome);
	}

	
	
}
