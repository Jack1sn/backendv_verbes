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

        entity.setPronome(pronomeRepo.findById(dto.getPronomeId()).orElseThrow());
        entity.setVerboInfinitivo(verboRepo.findById(dto.getVerboInfinitivoId()).orElseThrow());
        entity.setComplemento(complementoRepo.findById(dto.getComplementoId()).orElseThrow());
        entity.setTempoVerbal(tempoRepo.findById(dto.getTempoVerbalId()).orElseThrow());
        entity.setRespostaCorreta(dto.getRespostaCorreta());

        FraseAmbienteCasaEntity saved = fraseRepo.save(entity);
        return toDTO(saved);
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

        String fraseMontada = String.format("(%s) [%s] _______ [%s] [%s]%s",
                tempo, pronome, verbo, complemento, pontuacao);

        dto.setDescricaoMontada(fraseMontada);
        return dto;
    }
}
