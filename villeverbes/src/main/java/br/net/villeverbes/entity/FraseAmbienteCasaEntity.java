package br.net.villeverbes.entity;



import jakarta.persistence.*;

@Entity
@Table(name = "tb_frases_ambientes")
public class FraseAmbienteCasaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com a entidade Pronome
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pronome_id", nullable = false)
   
    private PronomeEntity pronome;

    @Column(name = "pronome_texto", nullable = false)
    private String pronomeTexto;  // Campo adicional para armazenar o texto do pronome

    // Relacionamento com a entidade VerboInfinitivo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verbo_infinitivo_id", nullable = false)
    private VerboInfinitivoEntity verboInfinitivo;

    @Column(name = "verbo_infinitivo_texto", nullable = false)
    private String verboInfinitivoTexto;  // Campo adicional para armazenar o texto do verbo

    // Relacionamento com a entidade Complemento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complemento_id", nullable = false)
    private ComplementoEntity complemento;

    @Column(name = "complemento_descricao", nullable = false)
    private String complementoDescricao;  // Campo adicional para armazenar a descrição do complemento

    // Relacionamento com a entidade TempoVerbal
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tempo_verbal_id", nullable = false)
    private TempoVerbalEntity tempo;

    @Column(name = "tempo_verbal_texto", nullable = false)
    private String tempoVerbalTexto;  // Campo adicional para armazenar o texto do tempo verbal

    @Column(name = "resposta_correta", nullable = false)
    private String respostaCorreta;

    // Construtor padrão
    public FraseAmbienteCasaEntity() {}

    // Construtor com campos
    public FraseAmbienteCasaEntity(PronomeEntity pronome,
                                   VerboInfinitivoEntity verboInfinitivo,
                                   ComplementoEntity complemento,
                                   TempoVerbalEntity tempo,
                                   String respostaCorreta) {
        this.pronome = pronome;
        this.verboInfinitivo = verboInfinitivo;
        this.complemento = complemento;
        this.tempo = tempo;
        this.respostaCorreta = respostaCorreta;

        // Preencher os campos de texto
        this.pronomeTexto = pronome != null ? pronome.getTexto() : null;
        this.verboInfinitivoTexto = verboInfinitivo != null ? verboInfinitivo.getVerbo() : null;
        this.complementoDescricao = complemento != null ? complemento.getDescricao() : null;
        this.tempoVerbalTexto = tempo != null ? tempo.getTempo() : null;
    }

    // Métodos set e get para os campos da entidade

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PronomeEntity getPronome() {
        return pronome;
    }

    public void setPronome(PronomeEntity pronome) {
        this.pronome = pronome;
        this.pronomeTexto = pronome != null ? pronome.getTexto() : null;
    }

    public String getPronomeTexto() {
        return pronomeTexto;
    }

    public void setPronomeTexto(String pronomeTexto) {
        this.pronomeTexto = pronomeTexto;
    }

    public VerboInfinitivoEntity getVerboInfinitivo() {
        return verboInfinitivo;
    }

    public void setVerboInfinitivo(VerboInfinitivoEntity verboInfinitivo) {
        this.verboInfinitivo = verboInfinitivo;
        this.verboInfinitivoTexto = verboInfinitivo != null ? verboInfinitivo.getVerbo() : null;
    }

    public String getVerboInfinitivoTexto() {
        return verboInfinitivoTexto;
    }

    public void setVerboInfinitivoTexto(String verboInfinitivoTexto) {
        this.verboInfinitivoTexto = verboInfinitivoTexto;
    }

    public ComplementoEntity getComplemento() {
        return complemento;
    }

    public void setComplemento(ComplementoEntity complemento) {
        this.complemento = complemento;
        this.complementoDescricao = complemento != null ? complemento.getDescricao() : null;
    }

    public String getComplementoDescricao() {
        return complementoDescricao;
    }

    public void setComplementoDescricao(String complementoDescricao) {
        this.complementoDescricao = complementoDescricao;
    }

    public TempoVerbalEntity getTempoVerbal() {
        return tempo;
    }

    public void setTempoVerbal(TempoVerbalEntity tempo) {
        this.tempo = tempo;
        this.tempoVerbalTexto = tempo != null ? tempo.getTempo() : null;
    }

    public String getTempoVerbalTexto() {
        return tempoVerbalTexto;
    }

    public void setTempoVerbalTexto(String tempoVerbalTexto) {
        this.tempoVerbalTexto = tempoVerbalTexto;
    }

    public String getRespostaCorreta() {
        return respostaCorreta;
    }

    public void setRespostaCorreta(String respostaCorreta) {
        this.respostaCorreta = respostaCorreta;
    }

    // Método toString para mostrar tudo
    @Override
    public String toString() {
        return "FraseAmbienteCasaEntity{" +
                "id=" + id +
                ", pronomeId=" + (pronome != null ? pronome.getId() : null) +
                ", pronomeTexto='" + pronomeTexto + '\'' +
                ", verboInfinitivoId=" + (verboInfinitivo != null ? verboInfinitivo.getId() : null) +
                ", verboInfinitivoTexto='" + verboInfinitivoTexto + '\'' +
                ", complementoId=" + (complemento != null ? complemento.getId() : null) +
                ", complementoDescricao='" + complementoDescricao + '\'' +
                ", tempoVerbalId=" + (tempo != null ? tempo.getId() : null) +
                ", tempoVerbalTexto='" + tempoVerbalTexto + '\'' +
                ", respostaCorreta='" + respostaCorreta + '\'' +
                '}';
    }
}
