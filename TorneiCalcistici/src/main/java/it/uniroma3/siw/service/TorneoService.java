package it.uniroma3.siw.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.dto.ClassificaRowDTO;
import it.uniroma3.siw.dto.PartitaDTO;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.repository.PartitaRepository;
import it.uniroma3.siw.repository.TorneoRepository;

@Service
public class TorneoService 
{

	private final TorneoRepository repository;
	private final PartitaRepository partitaRepository;
	
	public TorneoService(TorneoRepository repository, PartitaRepository partitaRepository)
	{
		this.repository = repository;
		this.partitaRepository = partitaRepository;
	}
	
	public Torneo findById(int id)
	{
		return repository.findById(id).get();
	}

	public List<ClassificaRowDTO> getClassifica(int id) {
		return repository.getClassifica(id).stream()
				.map(this::mapClassificaRow)
				.collect(Collectors.toList());
	}

	public List<PartitaDTO> getCalendario(int id) {
	    return partitaRepository.findByTorneoId(id).stream()
	            .sorted(java.util.Comparator.comparing(Partita::getDataOra))
	            .map(this::mapPartita)
	            .collect(Collectors.toList());
	}

	private ClassificaRowDTO mapClassificaRow(Object[] row) {
		String squadra = (String) row[1];
		int punti = ((Number) row[2]).intValue();
		int giocate = ((Number) row[3]).intValue();
		int golFatti = ((Number) row[4]).intValue();
		int golSubiti = ((Number) row[5]).intValue();
		return new ClassificaRowDTO(squadra, punti, giocate, golFatti, golSubiti);
	}

	private PartitaDTO mapPartita(Partita partita) {
		String squadraCasa = partita.getSquadraHome() != null ? partita.getSquadraHome().getNome() : null;
		String squadraOspite = partita.getSquadraAway() != null ? partita.getSquadraAway().getNome() : null;
		return new PartitaDTO(
				partita.getId(), 
				squadraCasa, 
				squadraOspite, 
				partita.getGoalsHome(), 
				partita.getGoalsAway(),
				partita.getDataOra()
				);
	}
	
	public List<Torneo> findAll()
	{
		java.util.List<Torneo> list = new java.util.ArrayList<>();
		repository.findAll().forEach(list::add);
		return list;
	}
	
	public Torneo save(Torneo saveMe) 
	{
		return this.repository.save(saveMe);
	}

	public TorneoRepository getRepository() {
		return repository;
	}
}

