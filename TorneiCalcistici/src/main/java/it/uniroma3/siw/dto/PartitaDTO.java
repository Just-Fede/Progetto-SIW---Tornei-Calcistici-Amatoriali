package it.uniroma3.siw.dto;

import java.time.LocalDateTime;

public class PartitaDTO {

    private int id;
    private String squadraCasa;
    private String squadraOspite;
    private int golCasa;
    private int golOspite;
    private LocalDateTime dataOra;

    public PartitaDTO(int id, String squadraCasa, String squadraOspite, int golCasa, int golOspite, LocalDateTime dataOra) {
        this.id = id;
        this.squadraCasa = squadraCasa;
        this.squadraOspite = squadraOspite;
        this.golCasa = golCasa;
        this.golOspite = golOspite;
        this.dataOra = dataOra;
    }

    public int getId() {
        return id;
    }

    public String getSquadraCasa() {
        return squadraCasa;
    }

    public String getSquadraOspite() {
        return squadraOspite;
    }

    public int getGolCasa() {
        return golCasa;
    }

    public int getGolOspite() {
        return golOspite;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }
}