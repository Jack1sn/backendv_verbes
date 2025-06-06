package br.net.villeverbes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_ambiente_casa")
public class FraseAmbienteCasaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pronome_id", nullable = false)
    private PronomeEntity pronome;

    @ManyToOne
    @JoinColumn(name = "verbo_infinitivo_id", nullable = false)
    private VerboInfinitivoEntity verboInfinitivo;

    @ManyToOne
    @JoinColumn(name = "complemento_id", nullable = false)
    private ComplementoEntity complemento;

    @ManyToOne
    @JoinColumn(name = "tempo_verbal_id", nullable = false)
    private TempoVerbalEntity tempo;

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
    }

    // Getters e Setters
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
    }

    public VerboInfinitivoEntity getVerboInfinitivo() {
        return verboInfinitivo;
    }

    public void setVerboInfinitivo(VerboInfinitivoEntity verboInfinitivo) {
        this.verboInfinitivo = verboInfinitivo;
    }

    public ComplementoEntity getComplemento() {
        return complemento;
    }

    public void setComplemento(ComplementoEntity complemento) {
        this.complemento = complemento;
    }

    public TempoVerbalEntity getTempoVerbal() {
        return tempo;
    }

    public void setTempoVerbal(TempoVerbalEntity tempoVerbal) {
        this.tempo = tempoVerbal;
    }

    public String getRespostaCorreta() {
        return respostaCorreta;
    }

    public void setRespostaCorreta(String respostaCorreta) {
        this.respostaCorreta = respostaCorreta;
    }

    @Override
    public String toString() {
        return "FraseAmbienteCasaEntity{" +
                "id=" + id +
                ", pronome=" + pronome +
                ", verboInfinitivo=" + verboInfinitivo +
                ", complemento=" + complemento +
                ", tempo=" + tempo +
                ", respostaCorreta='" + respostaCorreta + '\'' +
                '}';
    }
}
