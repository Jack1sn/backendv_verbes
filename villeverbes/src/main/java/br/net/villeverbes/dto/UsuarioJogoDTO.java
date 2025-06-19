package br.net.villeverbes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class UsuarioJogoDTO {

    private Long id;
    private String personagem;    // Nome do personagem (se aplicável)
    private String ambiente;      // Ambiente do jogo (e.g., 'casa', 'rua', etc.)
    private int acertos;          // Número de acertos no jogo
    private int total;            // Total de tentativas
    private String acertoPorAmbiente;  // Descrição do desempenho (e.g., "1 de 11")
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate data;       // Data em que o jogo foi realizado
    
    private boolean resultado;    // Status do resultado (ex: se o jogador ganhou ou não)

    // Construtor sem parâmetros
    public UsuarioJogoDTO() {}

    // Construtor com os campos principais do DTO
    public UsuarioJogoDTO(String personagem, String ambiente, int acertos, int total, String acertoPorAmbiente, LocalDate data, boolean resultado) {
        this.personagem = personagem;
        this.ambiente = ambiente;
        this.acertos = acertos;
        this.total = total;
        this.acertoPorAmbiente = acertoPorAmbiente;
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

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getAcertoPorAmbiente() {
        return acertoPorAmbiente;
    }

    public void setAcertoPorAmbiente(String acertoPorAmbiente) {
        this.acertoPorAmbiente = acertoPorAmbiente;
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
                ", ambiente='" + ambiente + '\'' +
                ", acertos=" + acertos +
                ", total=" + total +
                ", acertoPorAmbiente='" + acertoPorAmbiente + '\'' +
                ", data=" + data +
                ", resultado=" + resultado +
                '}';
    }
}
