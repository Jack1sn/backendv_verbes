package br.net.villeverbes.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailAjudaDTO {

    @NotBlank(message = "O campo remetente é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    private String remetente;

    @NotBlank(message = "A mensagem não pode estar em branco.")
    private String mensagem;

    private String resposta;
    private LocalDateTime dataEnvio;

    public EmailAjudaDTO() {}

    public EmailAjudaDTO(String remetente, String mensagem, String resposta, LocalDateTime dataEnvio) {
        this.remetente = remetente;
        this.mensagem = mensagem;
        this.resposta = resposta;
        this.dataEnvio = dataEnvio;
    }

    // Getters e Setters
    public String getRemetente() { return remetente; }
    public void setRemetente(String remetente) { this.remetente = remetente; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getResposta() { return resposta; }
    public void setResposta(String resposta) { this.resposta = resposta; }

    public LocalDateTime getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(LocalDateTime dataEnvio) { this.dataEnvio = dataEnvio; }



    @Override
    public String toString() {
        return "EmailAjudaDTO{" +
                "remetente='" + remetente + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", resposta='" + resposta + '\'' +
                ", dataEnvio=" + dataEnvio +
                '}';
    }
}
