
package br.net.villeverbes.dto;

public class SeloMedalhaDTO {

    private Long id;
    private Long usuarioJogoId; // Aqui você pode armazenar apenas o ID do usuário do jogo
    private int seloCasa;
    private int seloParque;
    private int seloUniversidade;
    private int medalha;

    // Construtores, Getters e Setters
    public SeloMedalhaDTO(Long id, Long usuarioJogoId, int seloCasa, int seloParque, int seloUniversidade, int medalha) {
        this.id = id;
        this.usuarioJogoId = usuarioJogoId;
        this.seloCasa = seloCasa;
        this.seloParque = seloParque;
        this.seloUniversidade = seloUniversidade;
        this.medalha = medalha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioJogoId() {
        return usuarioJogoId;
    }

    public void setUsuarioJogoId(Long usuarioJogoId) {
        this.usuarioJogoId = usuarioJogoId;
    }

    public int getSeloCasa() {
        return seloCasa;
    }

    public void setSeloCasa(int seloCasa) {
        this.seloCasa = seloCasa;
    }

    public int getSeloParque() {
        return seloParque;
    }

    public void setSeloParque(int seloParque) {
        this.seloParque = seloParque;
    }

    public int getSeloUniversidade() {
        return seloUniversidade;
    }

    public void setSeloUniversidade(int seloUniversidade) {
        this.seloUniversidade = seloUniversidade;
    }

    public int getMedalha() {
        return medalha;
    }

    public void setMedalha(int medalha) {
        this.medalha = medalha;
    }
}
