package br.net.villeverbes.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "tb_complemento")
public class ComplementoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String descricao;

    // Construtores
    public ComplementoEntity() {}

    public ComplementoEntity(String descricao) {
        this.descricao = descricao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // toString
    @Override
    public String toString() {
        return "ComplementoEntity{" +
                "id=" + id +
                ", descricao='" +  descricao + '\'' +
                '}';
    }
}
