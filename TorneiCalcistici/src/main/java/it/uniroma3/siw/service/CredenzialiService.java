package it.uniroma3.siw.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.repository.CredenzialiRepository;

@Service
public class CredenzialiService {

	private final CredenzialiRepository credenzialiRepository;
	private final PasswordEncoder passwordEncoder;

	public CredenzialiService
		(
			CredenzialiRepository credenezialiRepository, 
			PasswordEncoder passwordEncoder
		) 
	{
		this.credenzialiRepository = credenezialiRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Credenziali getCredenziali(Long id) {
		return this.credenzialiRepository.findById(id).get();
	}

	public Credenziali getCredenziali(String username) {
		return this.credenzialiRepository.findByUsername(username).get();
	}

	public void register(String username, String password, String role) {

		Credenziali c = new Credenziali();
		c.setUsername(username);

		c.setPassword(passwordEncoder.encode(password));

		c.setRole(role);

		credenzialiRepository.save(c);
	}
}
