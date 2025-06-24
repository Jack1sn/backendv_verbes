package br.net.villeverbes.model;

import br.net.villeverbes.entity.EmailAjudaEntity;

import java.time.LocalDateTime;

public class EmailAjudaModel {

    private Long id;
    private String remetente;
    private String mensagem;
    private LocalDateTime dataEnvio;
    private String resposta;

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

    public EmailAjudaModel(Long id, String remetente, String mensagem, LocalDateTime dataEnvio, String resposta) {
        this.id = id;
        this.remetente = remetente;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
        this.resposta = resposta;
    }

    // ✅ Getters e Setters
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

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    // ✅ Conversão para Entity
    public EmailAjudaEntity toEntity() {
        EmailAjudaEntity entity = new EmailAjudaEntity();
        entity.setId(this.id);
        entity.setEmailRemetente(this.remetente);
        entity.setMensagem(this.mensagem);
        entity.setDataEnvio(this.dataEnvio != null ? this.dataEnvio : LocalDateTime.now());
        entity.setResposta(this.resposta);
        return entity;
    }

    // ✅ Conversão de Entity para Model
    public static EmailAjudaModel fromEntity(EmailAjudaEntity entity) {
        if (entity == null) return null;

        EmailAjudaModel model = new EmailAjudaModel();
        model.setId(entity.getId());
        model.setRemetente(entity.getEmailRemetente());
        model.setMensagem(entity.getMensagem());
        model.setDataEnvio(entity.getDataEnvio());
        model.setResposta(entity.getResposta());
        return model;
    }
}
