package br.net.villeverbes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_tempo_verbal")

public class TempoVerbal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_tempo_verbal")
    
    private int id;
    @Column(name = "nome_tempo_verbal")
    private String tempoVerbal;

    public TempoVerbal() {
    }

    public TempoVerbal(int id, String tempoVerbal) {
        this.id = id;
        this.tempoVerbal = tempoVerbal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTempoVerbal() {
        return tempoVerbal;
    }

    public void setTempoVerbal(String tempoVerbal) {
        this.tempoVerbal = tempoVerbal;
    }

}
