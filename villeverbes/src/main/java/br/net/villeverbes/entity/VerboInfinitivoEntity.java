package br.net.villeverbes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_verbo_infinitivo")
public class VerboInfinitivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String verbo;

    // Construtores
    public VerboInfinitivoEntity() {}

    public VerboInfinitivoEntity(String verbo) {
        this.verbo = verbo;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVerbo() {
        return verbo;
    }

    public void setVerbo(String verbo) {
        this.verbo = verbo;
    }

    // toString
    @Override
    public String toString() {
        return "VerboInfinitivoEntity{" +
                "id=" + id +
                ", verbo='" + verbo + '\'' +
                '}';
    }
}
