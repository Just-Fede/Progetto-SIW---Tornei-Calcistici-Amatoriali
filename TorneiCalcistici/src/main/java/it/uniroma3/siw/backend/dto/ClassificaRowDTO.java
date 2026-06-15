package it.uniroma3.siw.backend.dto;

public class ClassificaRowDTO {

    private final String squadra;
    private final int punti;
    private final int giocate;
    private final int golFatti;
    private final int golSubiti;

    public ClassificaRowDTO(String squadra, int punti, int giocate, int golFatti, int golSubiti) {
        this.squadra = squadra;
        this.punti = punti;
        this.giocate = giocate;
        this.golFatti = golFatti;
        this.golSubiti = golSubiti;
    }

    public String getSquadra() {
        return squadra;
    }

    public int getPunti() {
        return punti;
    }

    public int getGiocate() {
        return giocate;
    }

    public int getGolFatti() {
        return golFatti;
    }

    public int getGolSubiti() {
        return golSubiti;
    }
}
