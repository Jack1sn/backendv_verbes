package br.net.villeverbes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pronome_verbo_tempo_verbal")

public class PronomeVerboTempoVerbal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pronome_verbo_tempo_verbal")
    private int id;
    @Column(name = "id_pronome_fk")
    private int pronome;
    @Column(name = "id_verbo_fk")
    private int verbo;
    @Column(name = "id_tempo_verbal_fk")
    private int tempoVerbal;
    @Column(name = "verbo_conjugado")
    private String verboConjugado;

    public PronomeVerboTempoVerbal() {
    }

    public PronomeVerboTempoVerbal(int id, int pronome, int verbo, int tempoVerbal, String verboConjugado) {
        this.id = id;
        this.pronome = pronome;
        this.verbo = verbo;
        this.tempoVerbal = tempoVerbal;
        this.verboConjugado = verboConjugado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPronome() {
        return pronome;
    }

    public void setPronome(int pronome) {
        this.pronome = pronome;
    }

    public int getVerbo() {
        return verbo;
    }

    public void setVerbo(int verbo) {
        this.verbo = verbo;
    }

    public int getTempoVerbal() {
        return tempoVerbal;
    }

    public void setTempoVerbal(int tempoVerbal) {
        this.tempoVerbal = tempoVerbal;
    }

    public String getVerboConjugado() {
        return verboConjugado;
    }

    public void setVerboConjugado(String verboConjugado) {
        this.verboConjugado = verboConjugado;
    }

    /*
     * @Override
     * public int toString() {
     * return "PronomeVerboTempoVerbal{" +
     * "id=" + id +
     * ", pronome='" + pronome + '\'' +
     * ", verbo='" + verbo + '\'' +
     * ", tempoVerbal='" + tempoVerbal + '\'' +
     * '}';
     * }
     */
}