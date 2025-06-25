package br.net.villeverbes.dto;

public class RedefinirSenhaDTO {

    private String email;
    private String senhaAtual;
    private String novaSenha;

    public RedefinirSenhaDTO() {
    }

    public RedefinirSenhaDTO(String email, String senhaAtual, String novaSenha) {
        this.email = email;
        this.senhaAtual = senhaAtual;
        this.novaSenha = novaSenha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    @Override
    public String toString() {
        return "RedefinirSenhaDTO{" +
                "email='" + email + '\'' +
                ", senhaAtual='[PROTEGIDA]'" +
                ", novaSenha='[PROTEGIDA]'" +
                '}';
    }
}
