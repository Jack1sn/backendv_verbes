


package br.net.villeverbes.dto;

public class PronomeDTO {

    private Long id;
    private String texto;

   
    public PronomeDTO() {}

    public PronomeDTO(Long id, String texto) {
        this.id = id;
        this.texto = texto;
        
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "PronomeDTO{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                '}';
    }
}
