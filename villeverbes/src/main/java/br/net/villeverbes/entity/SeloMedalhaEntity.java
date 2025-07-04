package br.net.villeverbes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_selo_medalha")
public class SeloMedalhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selo_medalha_id")
    private Long id; // ID da tabela, gerado automaticamente

    // Relacionamento com a tabela "tb_usuario_jogo"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id") // Chave estrangeira para a tabela "tb_usuario_jogo"
    private UsuarioJogoEntity usuarioJogo;

    // Campos inteiros para indicar o status de cada selo e medalha
    private Integer seloCasa = 0;
    private Integer seloParque = 0;
    private Integer seloUniversidade = 0;
    private Integer medalha = 0;

    // ✅ Novo campo personagem
    @Column(name = "personagem", nullable = true)
    private String personagem;

    // Construtor padrão
    public SeloMedalhaEntity() {
    }

    // Construtor com parâmetros
    public SeloMedalhaEntity(UsuarioJogoEntity usuarioJogo) {
        this.usuarioJogo = usuarioJogo;
        this.personagem = usuarioJogo != null ? usuarioJogo.getPersonagem() : null; // Atribui automaticamente
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
        this.personagem = usuarioJogo != null ? usuarioJogo.getPersonagem() : null; // Atualiza automaticamente
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
                ", usuarioJogo=" + (usuarioJogo != null ? usuarioJogo.getId() : "não atribuído") +
                ", seloCasa=" + seloCasa +
                ", seloParque=" + seloParque +
                ", seloUniversidade=" + seloUniversidade +
                ", medalha=" + medalha +
                ", personagem='" + personagem + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeloMedalhaEntity that = (SeloMedalhaEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
