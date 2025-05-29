package br.net.villeverbes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailAjudaDTO {

    @NotBlank(message = "O campo remetente é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    private String remetente;

    @NotBlank(message = "A mensagem não pode estar em branco.")
    private String mensagem;

    public EmailAjudaDTO() {
    }

    public EmailAjudaDTO(String remetente, String mensagem) {
        this.remetente = remetente;
        this.mensagem = mensagem;
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




    // toString (opcional, útil para debug)
    @Override
    public String toString() {
        return "EmailAjudaDTO{" +
                "remetente='" + remetente + '\'' +
                ", mensagem='" + mensagem + '\'' +
                '}';
    }

}