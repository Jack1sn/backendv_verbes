package br.net.villeverbes.dto;

public class LoginResponseDTO {

    private String token;
    private String tipo = "Bearer";
    private String email;
    private UsuarioDTO usuario;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public LoginResponseDTO(String token, UsuarioDTO usuarioDTO) {
        this.token = token;
        this.tipo = "Bearer";
        this.usuario = usuarioDTO;
        this.email = usuarioDTO.getEmail();
    }

    // Getters e Setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
