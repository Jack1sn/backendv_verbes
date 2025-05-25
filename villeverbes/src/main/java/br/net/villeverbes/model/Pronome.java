package br.net.villeverbes.model;

//import javax.annotation.processing.Generated;

import jakarta.persistence.*;


@Entity
@Table(name = "tb_pronome")
public class Pronome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_pronome")
    private int id;
    @Column(name = "nome_pronome")
    private String pronome;

    public Pronome() {
    }

    public Pronome(int id, String pronome) {
        this.id = id;
        this.pronome = pronome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPronome() {
        return pronome;
    }

    public void setPronome(String pronome) {
        this.pronome = pronome;
    }
}
