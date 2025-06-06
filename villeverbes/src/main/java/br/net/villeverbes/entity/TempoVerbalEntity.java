
package br.net.villeverbes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_tempo_verbal")
public class TempoVerbalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tempo;

    // Construtores
    public TempoVerbalEntity() {}

    public TempoVerbalEntity(String tempo) {
        this.tempo = tempo;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    // toString
    @Override
    public String toString() {
        return "TempoVerbalEntity{" +
                "id=" + id +
                ", tempo='" + tempo + '\'' +
                '}';
    }
}
