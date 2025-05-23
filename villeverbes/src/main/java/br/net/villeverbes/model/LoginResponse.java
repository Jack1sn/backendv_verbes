package br.net.villeverbes.model;

import br.net.villeverbes.entity.UsuarioEntity;

public class LoginResponse {
    private UsuarioEntity usuario;
    private String message;

    public LoginResponse(UsuarioEntity usuario, String message) {
        this.usuario = usuario;
        this.message = message;
    }

    

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}