package br.net.villeverbes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class UsuarioJogoDTO {

    private Long id;
    private String personagem;    // Nome do personagem (se aplicável)
        // Ambiente do jogo (e.g., 'casa', 'rua', etc.)
    private int acertosCasa;       // Acertos na fase Casa
    private int acertosParque;     // Acertos na fase Parque
    private int acertosUniversidade;  // Acertos na fase Universidade
    private int totalAcertos;     // Total de acertos somados entre as fases

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;   // Data em que o jogo foi realizado
    
    private boolean resultado;    // Status do resultado (ex: se o jogador ganhou ou não)

    // Construtor sem parâmetros (necessário para frameworks como Jackson)
    public UsuarioJogoDTO() {}

    // Construtor com todos os campos
    public UsuarioJogoDTO(String personagem,  int acertosCasa, int acertosParque, 
                           int acertosUniversidade, int totalAcertos, LocalDate data, boolean resultado) {
        this.personagem = personagem;
        
        this.acertosCasa = acertosCasa;
        this.acertosParque = acertosParque;
        this.acertosUniversidade = acertosUniversidade;
        this.totalAcertos = totalAcertos;
        this.data = data;
        this.resultado = resultado;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonagem() {
        return personagem;
    }

    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }

   

  

    public int getAcertosCasa() {
        return acertosCasa;
    }

    public void setAcertosCasa(int acertosCasa) {
        this.acertosCasa = acertosCasa;
    }

    public int getAcertosParque() {
        return acertosParque;
    }

    public void setAcertosParque(int acertosParque) {
        this.acertosParque = acertosParque;
    }

    public int getAcertosUniversidade() {
        return acertosUniversidade;
    }

    public void setAcertosUniversidade(int acertosUniversidade) {
        this.acertosUniversidade = acertosUniversidade;
    }

    public int getTotalAcertos() {
        return totalAcertos;
    }

    public void setTotalAcertos(int totalAcertos) {
        this.totalAcertos = totalAcertos;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    // Método útil para representar o objeto em forma de string
    @Override
    public String toString() {
        return "UsuarioJogoDTO{" +
                "id=" + id +
                ", personagem='" + personagem + '\'' +
                
                ", acertosCasa=" + acertosCasa +
                ", acertosParque=" + acertosParque +
                ", acertosUniversidade=" + acertosUniversidade +
                ", totalAcertos=" + totalAcertos +
                ", data=" + data +
                ", resultado=" + resultado +
                '}';
    }
}
