package br.net.villeverbes.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailAjudaDTO {

    private Long id; // Adicionando o campo id

    @NotBlank(message = "O campo remetente é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    private String remetente;

    @NotBlank(message = "A mensagem não pode estar em branco.")
    private String mensagem;

    private String resposta;
    private LocalDateTime dataEnvio;

    // Construtor sem id
    public EmailAjudaDTO() {}

    // Construtor com id
    public EmailAjudaDTO(Long id, String remetente, String mensagem, String resposta, LocalDateTime dataEnvio) {
        this.id = id;
        this.remetente = remetente;
        this.mensagem = mensagem;
        this.resposta = resposta;
        this.dataEnvio = dataEnvio;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    @Override
    public String toString() {
        return "EmailAjudaDTO{" +
                "id=" + id + // Incluindo o id no toString
                ", remetente='" + remetente + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", resposta='" + resposta + '\'' +
                ", dataEnvio=" + dataEnvio +
                '}';
    }
}
