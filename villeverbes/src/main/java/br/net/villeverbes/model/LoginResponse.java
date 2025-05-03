package br.net.villeverbes.model;

public class LoginResponse {
    private Usuario usuario;
    private String message;

    public LoginResponse(Usuario usuario, String message) {
        this.usuario = usuario;
        this.message = message;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}