
package br.net.villeverbes.dto;

public class ComplementoDTO {

    private Long id;
    private String descricao;

    // Construtores
    public ComplementoDTO() {}

    public ComplementoDTO(Long id, String descricao) {
        this.id = id;
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
}
