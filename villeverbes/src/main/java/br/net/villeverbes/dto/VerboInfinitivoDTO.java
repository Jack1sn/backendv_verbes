package br.net.villeverbes.dto;


public class VerboInfinitivoDTO {

    private Long id;
    private String verbo;

    // Construtores
    public VerboInfinitivoDTO() {}

    public VerboInfinitivoDTO(Long id, String verbo) {
        this.id = id;
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
}
