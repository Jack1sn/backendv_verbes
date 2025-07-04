package br.net.villeverbes.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_usuario_jogo")
public class UsuarioJogoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID único para cada jogo do usuário

    @ManyToOne(fetch = FetchType.LAZY)  // Relacionamento ManyToOne com UsuarioEntity
  @JoinColumn(name = "usuario_id",referencedColumnName = "id", nullable = false)  // Nome da chave estrangeira (coluna na tabela)
  @JsonIgnore  
  private UsuarioEntity usuario;  // A referência ao usuário associado ao jogo

    @Column(nullable = false)
    private String personagem; // Nome ou identificação do personagem no jogo

    @Column(name = "nome_usuario", nullable = true)
    private String nomeUsuario;

    @Column(name = "acertos_casa", nullable = false)
    private int acertosCasa;  // Acertos na fase Casa

    @Column(name = "acertos_parque", nullable = false)
    private int acertosParque; // Acertos na fase Parque

    @Column(name = "acertos_universidade", nullable = false)
    private int acertosUniversidade; // Acertos na fase Universidade

    @Column(name = "total_acertos", nullable = false)
    private int totalAcertos;  // Total de acertos

    @Column(name = "data_jogo", nullable = false)
    private LocalDate data; // Data em que o jogo foi realizado

    // Construtor vazio (necessário para JPA)
    public UsuarioJogoEntity() {}

    // Construtor com todos os campos
    public UsuarioJogoEntity(UsuarioEntity usuario, String personagem, String nomeUsuario, 
                              int acertosCasa, int acertosParque, int acertosUniversidade, 
                              int totalAcertos, LocalDate data) {
        this.usuario = usuario;
        this.personagem = personagem;
        this.nomeUsuario = nomeUsuario;
        this.acertosCasa = acertosCasa;
        this.acertosParque = acertosParque;
        this.acertosUniversidade = acertosUniversidade;
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

   public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
   

    public int getAcertosCasa() {
        return acertosCasa;
    }

    public void setAcertosCasa(int acertosCasa) {
        this.acertosCasa = acertosCasa;
    }

    public int getAcertosParque() {
        return acertosParque;
    }

    public void setAcertosParque(int acertosParque) {
        this.acertosParque = acertosParque;
    }

    public int getAcertosUniversidade() {
        return acertosUniversidade;
    }

    public void setAcertosUniversidade(int acertosUniversidade) {
        this.acertosUniversidade = acertosUniversidade;
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
                ", acertosCasa=" + acertosCasa +
                ", acertosParque=" + acertosParque +
                ", acertosUniversidade=" + acertosUniversidade +
                ", totalAcertos=" + totalAcertos +
                ", data=" + data +
                '}';
    }
}
