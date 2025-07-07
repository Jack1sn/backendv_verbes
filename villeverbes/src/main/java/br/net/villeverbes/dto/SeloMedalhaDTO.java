package br.net.villeverbes.dto;

public class SeloMedalhaDTO {

    private Long id;
    private Long usuarioJogoId;
    private int seloCasa;
    private int seloParque;
    private int seloUniversidade;
    private int medalha;
    private int posicao;
    private String personagem; // NOVO CAMPO

    // Construtor completo COM personagem
    public SeloMedalhaDTO(Long id, Long usuarioJogoId, int seloCasa, int seloParque,
                          int seloUniversidade, int medalha, int posicao, String personagem) {
        this.id = id;
        this.usuarioJogoId = usuarioJogoId;
        this.seloCasa = seloCasa;
        this.seloParque = seloParque;
        this.seloUniversidade = seloUniversidade;
        this.medalha = medalha;
        this.posicao = posicao;
        this.personagem = personagem;
    }

    // Construtor sem personagem
    public SeloMedalhaDTO(Long id, Long usuarioJogoId, int seloCasa, int seloParque,
                          int seloUniversidade, int medalha, int posicao) {
        this(id, usuarioJogoId, seloCasa, seloParque, seloUniversidade, medalha, posicao, null);
    }

    // Getters e Setters
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

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public String getPersonagem() {
        return personagem;
    }

    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }
}
