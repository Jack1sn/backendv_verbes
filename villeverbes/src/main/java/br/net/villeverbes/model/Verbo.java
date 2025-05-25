package br.net.villeverbes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_verbo")

public class Verbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_verbo")
    private int id;
    @Column(name = "nome_verbo")
    private String verbo;

    public Verbo() {
    }

    public Verbo(int id, String verbo) {
        this.id = id;
        this.verbo = verbo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVerbo() {
        return verbo;
    }

    public void setVerbo(String verbo) {
        this.verbo = verbo;
    }
}