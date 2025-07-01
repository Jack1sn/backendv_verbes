
package br.net.villeverbes.dto;

public class TempoVerbalDTO {

    private Long id;
    private String tempo;

    // Construtores
    public TempoVerbalDTO() {}

    public TempoVerbalDTO(Long id, String tempo) {
        this.id = id;
        this.tempo = tempo;
    }

  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }
}
