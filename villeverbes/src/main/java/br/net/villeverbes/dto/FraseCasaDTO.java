package br.net.villeverbes.dto;

public class FraseCasaDTO {
    private Long id;

    // IDs usados para operações diretas com registros existentes
    private Long pronomeId;
    private Long verboInfinitivoId;
    private Long complementoId;
    private Long tempoVerbalId;

    // Textos usados para facilitar criação via frontend
    private String pronomeTexto;
    private String verboTexto;
    private String tempoVerbalTexto;
    private String complementoDescricao;

    // Outros campos
    private String respostaCorreta;
    private String descricaoMontada;

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

    public String getPronomeTexto() {
        return pronomeTexto;
    }

    public void setPronomeTexto(String pronomeTexto) {
        this.pronomeTexto = pronomeTexto;
    }

    public String getVerboTexto() {
        return verboTexto;
    }

    public void setVerboTexto(String verboTexto) {
        this.verboTexto = verboTexto;
    }

    public String getTempoVerbalTexto() {
        return tempoVerbalTexto;
    }

    public void setTempoVerbalTexto(String tempoVerbalTexto) {
        this.tempoVerbalTexto = tempoVerbalTexto;
    }

    public String getComplementoDescricao() {
        return complementoDescricao;
    }

    public void setComplementoDescricao(String complementoDescricao) {
        this.complementoDescricao = complementoDescricao;
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
}
