package it.uniroma3.siw.backend.dto;

public class PartitaDTO {

    private String squadraCasa;
    private String squadraOspite;
    private int golCasa;
    private int golOspite;

    public PartitaDTO(String squadraCasa, String squadraOspite, int golCasa, int golOspite) {
        this.squadraCasa = squadraCasa;
        this.squadraOspite = squadraOspite;
        this.golCasa = golCasa;
        this.golOspite = golOspite;
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
}