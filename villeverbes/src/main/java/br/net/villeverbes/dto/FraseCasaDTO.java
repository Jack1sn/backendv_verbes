package br.net.villeverbes.dto;

public class FraseCasaDTO {
    private Long id;
    private Long pronomeId;
    private Long verboInfinitivoId;
    private Long complementoId;
    private Long tempoVerbalId;
    private String respostaCorreta;
    private String descricaoMontada; // Frase montada
    private String complementoDescricao; // NOVO: usado ao digitar um complemento novo

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPronomeId() {
        return pronomeId;
    }

    public void setPronomeId(Long pronomeId) {
        this.pronomeId = pronomeId;
    }

    public Long getVerboInfinitivoId() {
        return verboInfinitivoId;
    }

    public void setVerboInfinitivoId(Long verboInfinitivoId) {
        this.verboInfinitivoId = verboInfinitivoId;
    }

    public Long getComplementoId() {
        return complementoId;
    }

    public void setComplementoId(Long complementoId) {
        this.complementoId = complementoId;
    }

    public Long getTempoVerbalId() {
        return tempoVerbalId;
    }

    public void setTempoVerbalId(Long tempoVerbalId) {
        this.tempoVerbalId = tempoVerbalId;
    }

    public String getRespostaCorreta() {
        return respostaCorreta;
    }

    public void setRespostaCorreta(String respostaCorreta) {
        this.respostaCorreta = respostaCorreta;
    }

    public String getDescricaoMontada() {
        return descricaoMontada;
    }

    public void setDescricaoMontada(String descricaoMontada) {
        this.descricaoMontada = descricaoMontada;
    }

    public String getComplementoDescricao() {
        return complementoDescricao;
    }

    public void setComplementoDescricao(String complementoDescricao) {
        this.complementoDescricao = complementoDescricao;
    }
}
