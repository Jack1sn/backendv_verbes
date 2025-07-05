package br.net.villeverbes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_selo_medalha")
public class SeloMedalhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selo_medalha_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_jogo_id", nullable = false, unique = true) // id do jogo
    private UsuarioJogoEntity usuarioJogo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)  // referência para o usuário
    private UsuarioEntity usuario;  

    @Column(name = "selo_casa")
    private Integer seloCasa = 0;

    @Column(name = "selo_parque")
    private Integer seloParque = 0;

    @Column(name = "selo_universidade")
    private Integer seloUniversidade = 0;

    @Column(name = "medalha")
    private Integer medalha = 0;

    @Column(name = "personagem")
    private String personagem;

    // Construtor padrão
    public SeloMedalhaEntity() {}

    // Construtor com lógica automática do personagem e usuário
    public SeloMedalhaEntity(UsuarioJogoEntity usuarioJogo) {
        this.usuarioJogo = usuarioJogo;
        this.usuario = usuarioJogo != null ? usuarioJogo.getUsuario() : null;  // Pega usuário do jogo
        this.personagem = usuarioJogo != null ? usuarioJogo.getPersonagem() : null;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioJogoEntity getUsuarioJogo() {
        return usuarioJogo;
    }

    public void setUsuarioJogo(UsuarioJogoEntity usuarioJogo) {
        this.usuarioJogo = usuarioJogo;
        this.usuario = usuarioJogo != null ? usuarioJogo.getUsuario() : null;
        this.personagem = usuarioJogo != null ? usuarioJogo.getPersonagem() : null;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public Integer getSeloCasa() {
        return seloCasa;
    }

    public void setSeloCasa(Integer seloCasa) {
        this.seloCasa = seloCasa;
    }

    public Integer getSeloParque() {
        return seloParque;
    }

    public void setSeloParque(Integer seloParque) {
        this.seloParque = seloParque;
    }

    public Integer getSeloUniversidade() {
        return seloUniversidade;
    }

    public void setSeloUniversidade(Integer seloUniversidade) {
        this.seloUniversidade = seloUniversidade;
    }

    public Integer getMedalha() {
        return medalha;
    }

    public void setMedalha(Integer medalha) {
        this.medalha = medalha;
    }

    public String getPersonagem() {
        return personagem;
    }

    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }

    @Override
    public String toString() {
        return "SeloMedalhaEntity{" +
                "id=" + id +
                ", usuarioJogoId=" + (usuarioJogo != null ? usuarioJogo.getId() : "null") +
                ", usuarioId=" + (usuario != null ? usuario.getId() : "null") +
                ", seloCasa=" + seloCasa +
                ", seloParque=" + seloParque +
                ", seloUniversidade=" + seloUniversidade +
                ", medalha=" + medalha +
                ", personagem='" + personagem + '\'' +
                '}';
    }
}
