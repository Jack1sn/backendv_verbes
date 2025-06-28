package br.net.villeverbes.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_usuario_jogo")
public class UsuarioJogoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID único para cada jogo do usuário

    @ManyToOne(fetch = FetchType.LAZY)  // Relacionamento ManyToOne com UsuarioEntity
  @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)  // Nome da chave estrangeira (coluna na tabela)
    private UsuarioEntity usuario;  // A referência ao usuário associado ao jogo

    @Column(nullable = false)
    private String personagem; // Nome ou identificação do personagem no jogo

    // Ambiente em que o jogo ocorreu (ex: "casa", "rua", etc.)

    @Column(name = "acerto_casa", nullable = false)
    private int acertoCasa;  // Acertos na fase Casa

    @Column(name = "acerto_parque", nullable = false)
    private int acertoParque; // Acertos na fase Parque

    @Column(name = "acerto_universidade", nullable = false)
    private int acertoUniversidade; // Acertos na fase Universidade

    @Column(name = "total_acertos", nullable = false)
    private int totalAcertos;  // Total de acertos

    @Column(name = "data_jogo", nullable = false)
    private LocalDate data; // Data em que o jogo foi realizado

    // Construtor vazio (necessário para JPA)
    public UsuarioJogoEntity() {}

    // Construtor com todos os campos
    public UsuarioJogoEntity(UsuarioEntity usuario, String personagem,  
                              int acertoCasa, int acertoParque, int acertoUniversidade, 
                              int totalAcertos, LocalDate data) {
        this.usuario = usuario;
        this.personagem = personagem;
   
        this.acertoCasa = acertoCasa;
        this.acertoParque = acertoParque;
        this.acertoUniversidade = acertoUniversidade;
        this.totalAcertos = totalAcertos;
        this.data = data;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public String getPersonagem() {
        return personagem;
    }

    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }

   
   

    public int getAcertoCasa() {
        return acertoCasa;
    }

    public void setAcertoCasa(int acertoCasa) {
        this.acertoCasa = acertoCasa;
    }

    public int getAcertoParque() {
        return acertoParque;
    }

    public void setAcertoParque(int acertoParque) {
        this.acertoParque = acertoParque;
    }

    public int getAcertoUniversidade() {
        return acertoUniversidade;
    }

    public void setAcertoUniversidade(int acertoUniversidade) {
        this.acertoUniversidade = acertoUniversidade;
    }

    public int getTotalAcertos() {
        return totalAcertos;
    }

    public void setTotalAcertos(int totalAcertos) {
        this.totalAcertos = totalAcertos;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    // Método útil para representar o objeto em forma de string (opcional)
    @Override
    public String toString() {
        return "UsuarioJogoEntity{" +
                "id=" + id +
                ", usuario=" + usuario.getNome() +  // Representando nome do usuário no toString
                ", personagem='" + personagem + '\'' +
                //", ambientes='\Todos' +
                ", acertoCasa=" + acertoCasa +
                ", acertoParque=" + acertoParque +
                ", acertoUniversidade=" + acertoUniversidade +
                ", totalAcertos=" + totalAcertos +
                ", data=" + data +
                '}';
    }
}
