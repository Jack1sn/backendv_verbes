package br.net.villeverbes.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_jogo_usuario")
public class UsuarioJogoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID único para cada jogo do usuário

    @ManyToOne(fetch = FetchType.LAZY)  // Relacionamento ManyToOne com UsuarioEntity
    @JoinColumn(name = "usuario_id", nullable = false)  // Nome da chave estrangeira (coluna na tabela)
    private UsuarioEntity usuario;  // A referência ao usuário associado ao jogo

    @Column(nullable = false)
    private String personagem; // Nome ou identificação do personagem no jogo

    @Column(nullable = false)
    private String ambiente; // Ambiente em que o jogo ocorreu (ex: "casa", "rua", etc.)

    @Column(nullable = false)
    private int acertos; // Número de acertos no jogo

    @Column(nullable = false)
    private int total; // Total de tentativas no jogo

    @Column(name = "acerto_por_ambiente", nullable = false)
    private String acertoPorAmbiente; // Desempenho no formato "1 de 11"

    @Column(name = "data_jogo", nullable = false)
    private LocalDate data; // Data em que o jogo foi realizado

    @Column(nullable = false)
    private boolean resultado; // Resultado do jogo (true = vitória, false = derrota)

    // Construtor vazio (necessário para JPA)
    public UsuarioJogoEntity() {}

    // Construtor com todos os campos
    public UsuarioJogoEntity(UsuarioEntity usuario, String personagem, String ambiente, int acertos, 
                              int total, String acertoPorAmbiente, LocalDate data, boolean resultado) {
        this.usuario = usuario;
        this.personagem = personagem;
        this.ambiente = ambiente;
        this.acertos = acertos;
        this.total = total;
        this.acertoPorAmbiente = acertoPorAmbiente;
        this.data = data;
        this.resultado = resultado;
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

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getAcertoPorAmbiente() {
        return acertoPorAmbiente;
    }

    public void setAcertoPorAmbiente(String acertoPorAmbiente) {
        this.acertoPorAmbiente = acertoPorAmbiente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    // Método útil para representar o objeto em forma de string (opcional)
    @Override
    public String toString() {
        return "UsuarioJogoEntity{" +
                "id=" + id +
                ", usuario=" + usuario.getNome() +  // Representando nome do usuário no toString
                ", personagem='" + personagem + '\'' +
                ", ambiente='" + ambiente + '\'' +
                ", acertos=" + acertos +
                ", total=" + total +
                ", acertoPorAmbiente='" + acertoPorAmbiente + '\'' +
                ", data=" + data +
                ", resultado=" + resultado +
                '}';
    }
}
