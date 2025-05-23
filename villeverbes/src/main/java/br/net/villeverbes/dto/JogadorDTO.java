package br.net.villeverbes.dto;
public class JogadorDTO {
    private String nome;
    private Integer posicao;
    private String email;
    private String seloAmbiente;


    public JogadorDTO() {
        // Construtor vazio necessário para frameworks como Jackson
    }

    public JogadorDTO(String nome, int posicao, String seloAmbiente) {
        this.nome = nome;
        this.posicao = posicao;
        this.seloAmbiente = seloAmbiente;
    }

    // Getters e Setters

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



    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public String getSeloAmbiente() {
        return seloAmbiente;
    }

    public void setSeloAmbiente(String seloAmbiente) {
        this.seloAmbiente = seloAmbiente;
    }
}