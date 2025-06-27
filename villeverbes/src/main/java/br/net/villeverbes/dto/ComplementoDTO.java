package br.net.villeverbes.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ComplementoDTO {

    private Long id;
    private String descricao;

    // Construtor padrão necessário para algumas operações
    public ComplementoDTO() {
    }

    // Construtor com @JsonCreator para desserialização do JSON
    @JsonCreator
    public ComplementoDTO(@JsonProperty("id") Long id, @JsonProperty("descricao") String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    // Getters e setters
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
}
