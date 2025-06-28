package br.net.villeverbes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class UsuarioJogoDTO {

    private Long id;
    private String personagem;    // Nome do personagem (se aplicável)
        // Ambiente do jogo (e.g., 'casa', 'rua', etc.)
    private int acertoCasa;       // Acertos na fase Casa
    private int acertoParque;     // Acertos na fase Parque
    private int acertoUniversidade;  // Acertos na fase Universidade
    private int totalAcertos;     // Total de acertos somados entre as fases

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;   // Data em que o jogo foi realizado
    
    private boolean resultado;    // Status do resultado (ex: se o jogador ganhou ou não)

    // Construtor sem parâmetros (necessário para frameworks como Jackson)
    public UsuarioJogoDTO() {}

    // Construtor com todos os campos
    public UsuarioJogoDTO(String personagem,  int acertoCasa, int acertoParque, 
                           int acertoUniversidade, int totalAcertos, LocalDate data, boolean resultado) {
        this.personagem = personagem;
        
        this.acertoCasa = acertoCasa;
        this.acertoParque = acertoParque;
        this.acertoUniversidade = acertoUniversidade;
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

   

  

    public int getAcertoCasa() {
        return acertoCasa;
    }

    public void setAcertoCasa(int acertoCasa) {
        this.acertoCasa = acertoCasa;
    }

    public int getAcertoParque() {
        return acertoParque;
    }

    public void setAcertoParque(int acertoParque) {
        this.acertoParque = acertoParque;
    }

    public int getAcertoUniversidade() {
        return acertoUniversidade;
    }

    public void setAcertoUniversidade(int acertoUniversidade) {
        this.acertoUniversidade = acertoUniversidade;
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
                
                ", acertoCasa=" + acertoCasa +
                ", acertoParque=" + acertoParque +
                ", acertoUniversidade=" + acertoUniversidade +
                ", totalAcertos=" + totalAcertos +
                ", data=" + data +
                ", resultado=" + resultado +
                '}';
    }
}
