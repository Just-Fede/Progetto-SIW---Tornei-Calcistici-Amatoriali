package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String ruolo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
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
		Utente other = (Utente) obj;
		return id == other.id;
	}


	
	
}
