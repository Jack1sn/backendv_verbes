package br.net.villeverbes.entity;

import jakarta.persistence.*;

@Entity

@Table(name = "tb_pronome")
public class PronomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String texto;

    // --- Construtores ---
    public PronomeEntity() {
    }

    public PronomeEntity(String texto) {
        this.texto = texto;
    }

    public PronomeEntity(Long id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    // --- Getters e Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    // --- toString ---
    @Override
    public String toString() {
        return "PronomeEntity{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                '}';
    }
}
