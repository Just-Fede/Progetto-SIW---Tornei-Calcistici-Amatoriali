package it.uniroma3.siw.backend.dto;

public class TorneoDTO {

    private int id;
    private String nome;
    private int anno;
    private String descrizione;

    public TorneoDTO(int id, String nome, int anno, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.anno = anno;
        this.descrizione = descrizione;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getAnno() { return anno; }
    public String getDescrizione() { return descrizione; }
}