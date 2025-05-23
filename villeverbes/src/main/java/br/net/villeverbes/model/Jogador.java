package br.net.villeverbes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity

public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único do jogador

    @Column(nullable = false)
    private String nome; // Nome do jogador

    @Column(nullable = false, unique = true)
    private String email; // E-mail do jogador

    @Column(nullable = false)
    private String senha; // Senha gerada automaticamente

    // Construtor padrão (necessário para o JPA)
    public Jogador() {
    }

    // Construtor para facilitar o cadastro
    public Jogador(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters para todos os campos
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Jogador{id=" + id + ", nome='" + nome + "', email='" + email + "', senha='" + senha + "'}";
    }
}
