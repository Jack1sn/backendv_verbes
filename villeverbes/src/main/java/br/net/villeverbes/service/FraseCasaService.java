package br.net.villeverbes.service;

import br.net.villeverbes.dto.FraseCasaDTO;
import br.net.villeverbes.entity.*;
import br.net.villeverbes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FraseCasaService {

    @Autowired
    private FraseCasaRepository fraseRepo;

    @Autowired
    private PronomeRepository pronomeRepo;

    @Autowired
    private VerboInfinitivoRepository verboRepo;

    @Autowired
    private ComplementoRepository complementoRepo;

    @Autowired
    private TempoVerbalRepository tempoRepo;

    public List<FraseCasaDTO> listarTodas() {
        return fraseRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<FraseCasaDTO> buscarPorId(Long id) {
        return fraseRepo.findById(id)
                .map(this::toDTO);
    }

  public FraseCasaDTO salvar(FraseCasaDTO dto) {
    FraseAmbienteCasaEntity entity;

    if (dto.getId() != null) {
        entity = fraseRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Frase não encontrada para atualizar."));
    } else {
        entity = new FraseAmbienteCasaEntity();
    }

    // ----------- PRONOME -----------
    if (dto.getPronomeId() != null) {
        entity.setPronome(pronomeRepo.findById(dto.getPronomeId()).orElseThrow());
    } else if (dto.getPronomeTexto() != null) {
        entity.setPronome(pronomeRepo.findByTexto(dto.getPronomeTexto())
                .orElseThrow(() -> new RuntimeException("Pronome não encontrado: " + dto.getPronomeTexto())));
    }

    // ----------- VERBO -------------
    if (dto.getVerboInfinitivoId() != null) {
        entity.setVerboInfinitivo(verboRepo.findById(dto.getVerboInfinitivoId()).orElseThrow());
    } else if (dto.getVerboTexto() != null) {
        entity.setVerboInfinitivo(verboRepo.findByVerbo(dto.getVerboTexto())
                .orElseThrow(() -> new RuntimeException("Verbo não encontrado: " + dto.getVerboTexto())));
    }

    // ----------- TEMPO VERBAL -----------
    if (dto.getTempoVerbalId() != null) {
        entity.setTempoVerbal(tempoRepo.findById(dto.getTempoVerbalId()).orElseThrow());
    } else if (dto.getTempoVerbalTexto() != null) {
        entity.setTempoVerbal(tempoRepo.findByTempo(dto.getTempoVerbalTexto())
                .orElseThrow(() -> new RuntimeException("Tempo verbal não encontrado: " + dto.getTempoVerbalTexto())));
    }

    // ----------- COMPLEMENTO -----------
    if (dto.getComplementoId() != null) {
        entity.setComplemento(complementoRepo.findById(dto.getComplementoId()).orElseThrow());
    } else if (dto.getComplementoDescricao() != null && !dto.getComplementoDescricao().isBlank()) {
        // Verifica se já existe um complemento igual (evita duplicatas)
        Optional<ComplementoEntity> existente = complementoRepo.findByDescricao(dto.getComplementoDescricao().trim());
        ComplementoEntity complemento = existente.orElseGet(() -> {
            ComplementoEntity novo = new ComplementoEntity();
            novo.setDescricao(dto.getComplementoDescricao().trim());
            return complementoRepo.save(novo);
        });
        entity.setComplemento(complemento);
    } else {
        throw new RuntimeException("Complemento é obrigatório.");
    }

    // ----------- RESPOSTA CORRETA -----------
    entity.setRespostaCorreta(dto.getRespostaCorreta());

    // ----------- SALVAR -----------
    FraseAmbienteCasaEntity salvo = fraseRepo.save(entity);
    return toDTO(salvo);
}


    public void deletar(Long id) {
        if (!fraseRepo.existsById(id)) {
            throw new RuntimeException("Frase não encontrada para deletar.");
        }
        fraseRepo.deleteById(id);
    }

    private FraseCasaDTO toDTO(FraseAmbienteCasaEntity entity) {
        FraseCasaDTO dto = new FraseCasaDTO();
        dto.setId(entity.getId());
        dto.setPronomeId(entity.getPronome().getId());
        dto.setVerboInfinitivoId(entity.getVerboInfinitivo().getId());
        dto.setComplementoId(entity.getComplemento().getId());
        dto.setTempoVerbalId(entity.getTempoVerbal().getId());
        dto.setRespostaCorreta(entity.getRespostaCorreta());

        // Construção da frase formatada
        String tempo = entity.getTempoVerbal().getTempo(); // Ex: "présent"
        String pronome = entity.getPronome().getTexto();   // Ex: "je"
        String verbo = entity.getVerboInfinitivo().getVerbo(); // Ex: "manger"
        String complemento = entity.getComplemento().getDescricao(); // Ex: "une pomme"
        String pontuacao = entity.getRespostaCorreta(); // Ex: "."

        String fraseMontada = String.format("(%s) \n%s _______ [%s] %s %s",
                tempo, pronome, verbo, complemento, pontuacao);
                

        dto.setDescricaoMontada(fraseMontada);
        return dto;
    }
}
