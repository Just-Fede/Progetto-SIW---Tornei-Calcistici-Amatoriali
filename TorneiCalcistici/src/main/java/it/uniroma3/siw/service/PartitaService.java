package it.uniroma3.siw.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Partecipazione;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.repository.PartecipazioneRepository;
import it.uniroma3.siw.repository.PartitaRepository;


@Service
public class PartitaService 
{

	private final PartitaRepository repository;
	private final PartecipazioneRepository partecipazioneService;
	
	public PartitaService(PartitaRepository repository, PartecipazioneRepository partecipazioneService)
	{
		this.repository = repository;
		this.partecipazioneService = partecipazioneService;
	}
	
	public Partita findById(int id)
	{
		return repository.findById(id).get();
	}
	
	
	public List<Partita> findAll()
	{
		java.util.List<Partita> list = new java.util.ArrayList<>();
		repository.findAll().forEach(list::add);
		return list;
	}
	
	public Partita save(Partita saveMe)
	{
	    Torneo t = saveMe.getTorneo();

	    creaPartecipazioneSeAssente(t, saveMe.getSquadraHome());
	    creaPartecipazioneSeAssente(t, saveMe.getSquadraAway());

	    return this.repository.save(saveMe);
	}

	private void creaPartecipazioneSeAssente(Torneo t, Squadra squadra)
	{
	    boolean esiste = t.getPartecipazioni().stream()
	            .anyMatch(p -> p.getSquadra().equals(squadra));

	    if (!esiste)
	    {
	        Partecipazione p = new Partecipazione();
	        p.setTorneo(t);
	        p.setSquadra(squadra);

	        this.partecipazioneService.save(p);
	        t.getPartecipazioni().add(p);
	    }
	}

	public void deleteById(Integer id) 
	{
		this.repository.deleteById(id);
		
	}

	public List<Partita> findAllComplete() 
	{
		return this.repository.findAllComplete();
	}

	public List<Partita> findPartiteAperte() 
	{
		return this.repository.findPartiteAperte();
	}
	
}
