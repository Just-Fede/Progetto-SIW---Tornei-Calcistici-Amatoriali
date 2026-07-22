package it.uniroma3.siw.dto;

import java.util.List;

public record TorneoDTO(
    int id,
    String nome,
    int anno,
    String descrizione,
    List<PartecipazioneDTO> partecipazioni
) {}