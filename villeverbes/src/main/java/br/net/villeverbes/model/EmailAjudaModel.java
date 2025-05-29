package br.net.villeverbes.model;

import br.net.villeverbes.entity.EmailAjudaEntity;

import java.time.LocalDateTime;

public class EmailAjudaModel {

    private String remetente;
    private String mensagem;
    private LocalDateTime dataEnvio;

    // ✅ Construtores
    public EmailAjudaModel() {
    }

    public EmailAjudaModel(String remetente, String mensagem) {
        this.remetente = remetente;
        this.mensagem = mensagem;
    }

    public EmailAjudaModel(String remetente, String mensagem, LocalDateTime dataEnvio) {
        this.remetente = remetente;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
    }

    // ✅ Getters e Setters
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

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    // ✅ Conversão para Entity
    public EmailAjudaEntity toEntity() {
        EmailAjudaEntity entity = new EmailAjudaEntity();
        entity.setEmailRemetente(this.remetente);
        entity.setMensagem(this.mensagem);
        entity.setDataEnvio(this.dataEnvio != null ? this.dataEnvio : LocalDateTime.now());
        return entity;
    }

    // ✅ Conversão estática de Entity para DTO
    public static EmailAjudaModel fromEntity(EmailAjudaEntity entity) {
        return new EmailAjudaModel(
                entity.getEmailRemetente(),
                entity.getMensagem(),
                entity.getDataEnvio()
        );
    }
}
