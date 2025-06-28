package br.net.villeverbes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_selo_medalha") // Mapeia para a tabela "tb_selo_medalha"
public class SeloMedalhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID da tabela, gerado automaticamente

    // Relacionamento com a tabela "tb_usuario_jogo"
    @ManyToOne(fetch = FetchType.LAZY)  // Usar o LAZY para carregar o relacionamento apenas quando necessário
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id") // Chave estrangeira para a tabela "tb_usuario_jogo"
    private UsuarioJogoEntity usuarioJogo;

    // Campos inteiros para indicar o status de cada selo e medalha
    private int seloCasa; // Armazena o status do selo da Casa (0 = Não obtido, 1 = Obtido)
    private int seloParque; // Armazena o status do selo do Parque (0 = Não obtido, 1 = Obtido)
    private int seloUniversidade; // Armazena o valor incremental do selo da Universidade (0 = Não obtido, valores maiores conforme o progresso)
    private int medalha; // Armazena o status da medalha (0 = Não obtido, 1 = Obtido)

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
    }

    public int getSeloCasa() {
        return seloCasa;
    }

    public void setSeloCasa(int seloCasa) {
        this.seloCasa = seloCasa;
    }

    public int getSeloParque() {
        return seloParque;
    }

    public void setSeloParque(int seloParque) {
        this.seloParque = seloParque;
    }

    public int getSeloUniversidade() {
        return seloUniversidade;
    }

    public void setSeloUniversidade(int seloUniversidade) {
        this.seloUniversidade = seloUniversidade;
    }

    public int getMedalha() {
        return medalha;
    }

    public void setMedalha(int medalha) {
        this.medalha = medalha;
    }

    // Método toString atualizado
    @Override
    public String toString() {
        return "SeloMedalhaEntity{" +
                "id=" + id +
                ", usuarioJogo=" + (usuarioJogo != null ? usuarioJogo.getId() : "não atribuído") +  // Evita NullPointerException
                ", seloCasa=" + seloCasa +
                ", seloParque=" + seloParque +
                ", seloUniversidade=" + seloUniversidade +
                ", medalha=" + medalha +
                '}';
    }
}
