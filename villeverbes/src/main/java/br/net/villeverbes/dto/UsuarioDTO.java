package br.net.villeverbes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import br.net.villeverbes.entity.UsuarioEntity;

import java.time.LocalDate;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String perfil;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    private String endereco;
    private String cpf;
    private String telefone;
    private String cep;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String login;
    private boolean actif;

    public UsuarioDTO() {}

    // ✅ Construtor que preenche os campos a partir da entidade
    public UsuarioDTO(UsuarioEntity usuarioEntity) {
        this.id = usuarioEntity.getId();
        this.nome = usuarioEntity.getNome();
        this.email = usuarioEntity.getEmail();
        this.senha = usuarioEntity.getSenha();
        this.perfil = usuarioEntity.getPerfil();
        this.dataNascimento = usuarioEntity.getDataNascimento();
        this.endereco = usuarioEntity.getEndereco();
        this.cpf = usuarioEntity.getCpf();
        this.telefone = usuarioEntity.getTelefone();
        this.cep = usuarioEntity.getCep();
        this.numero = usuarioEntity.getNumero();
        this.complemento = usuarioEntity.getComplemento();
        this.bairro = usuarioEntity.getBairro();
        this.cidade = usuarioEntity.getCidade();
        this.estado = usuarioEntity.getEstado();
        this.login = usuarioEntity.getLogin();
         this.actif = usuarioEntity.isActif();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
}
