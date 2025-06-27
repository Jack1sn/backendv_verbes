package br.net.villeverbes.service;

import br.net.villeverbes.dto.FraseCasaDTO;
import br.net.villeverbes.entity.*;
import br.net.villeverbes.repository.*;
import br.net.villeverbes.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // Listar todas as frases com carregamento das associações
    @Transactional(readOnly = true)
    public List<FraseCasaDTO> listarTodas() {
        return fraseRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar frase por ID
    @Transactional(readOnly = true)
    public Optional<FraseCasaDTO> buscarPorId(Long id) {
        return fraseRepo.findById(id)
                .map(this::toDTO);
    }

   @Transactional
public FraseCasaDTO salvar(FraseCasaDTO dto) {
    // Buscar ou criar a entidade
    FraseAmbienteCasaEntity entity = (dto.getId() != null)
            ? fraseRepo.findById(dto.getId()).orElseThrow(() -> new NotFoundException("Frase não encontrada para atualizar."))
            : new FraseAmbienteCasaEntity();

    // ----- PRONOME -----
    if (dto.getPronomeId() != null) {
        entity.setPronome(pronomeRepo.findById(dto.getPronomeId())
                .orElseThrow(() -> new NotFoundException("Pronome não encontrado.")));
    } else if (dto.getPronomeTexto() != null) {
        entity.setPronome(pronomeRepo.findByTexto(dto.getPronomeTexto())
                .orElseThrow(() -> new NotFoundException("Pronome não encontrado: " + dto.getPronomeTexto())));
    }

    // ----- VERBO -----
    if (dto.getVerboInfinitivoId() != null) {
        entity.setVerboInfinitivo(verboRepo.findById(dto.getVerboInfinitivoId())
                .orElseThrow(() -> new NotFoundException("Verbo não encontrado.")));
    } else if (dto.getVerboTexto() != null) {
        entity.setVerboInfinitivo(verboRepo.findByVerbo(dto.getVerboTexto())
                .orElseThrow(() -> new NotFoundException("Verbo não encontrado: " + dto.getVerboTexto())));
    }

    // ----- TEMPO VERBAL -----
    if (dto.getTempoVerbalId() != null) {
        entity.setTempoVerbal(tempoRepo.findById(dto.getTempoVerbalId())
                .orElseThrow(() -> new NotFoundException("Tempo verbal não encontrado.")));
    } else if (dto.getTempoVerbalTexto() != null) {
        entity.setTempoVerbal(tempoRepo.findByTempo(dto.getTempoVerbalTexto())
                .orElseThrow(() -> new NotFoundException("Tempo verbal não encontrado: " + dto.getTempoVerbalTexto())));
    }

    // ----- COMPLEMENTO -----
    if (dto.getComplementoId() != null) {
        entity.setComplemento(complementoRepo.findById(dto.getComplementoId())
                .orElseThrow(() -> new NotFoundException("Complemento não encontrado.")));
    } else if (dto.getComplementoDescricao() != null && !dto.getComplementoDescricao().isBlank()) {
        // Buscar complemento existente
        Optional<ComplementoEntity> existente = complementoRepo.findByDescricao(dto.getComplementoDescricao().trim());
        
        // Se não existir, cria um novo complemento
        ComplementoEntity complemento = existente.orElseGet(() -> {
            ComplementoEntity novo = new ComplementoEntity();
            novo.setDescricao(dto.getComplementoDescricao().trim());
            return complementoRepo.save(novo);  // Salva o novo complemento
        });
        
        entity.setComplemento(complemento);
    } else {
        // Se não encontrar nem o ID nem a descrição, lança uma exceção específica
        throw new NotFoundException("Complemento é obrigatório.");
    }

    // ----- RESPOSTA CORRETA -----
    entity.setRespostaCorreta(dto.getRespostaCorreta());

    // Salvar ou atualizar a entidade
    FraseAmbienteCasaEntity salvo = fraseRepo.save(entity);

    // Retorna o DTO com os dados atualizados
    return toDTO(salvo);
}

    // Deletar uma frase
    @Transactional
    public void deletar(Long id) {
        if (!fraseRepo.existsById(id)) {
            throw new NotFoundException("Frase não encontrada para deletar.");
        }
        fraseRepo.deleteById(id);
    }

    // Converter a entidade para DTO
    private FraseCasaDTO toDTO(FraseAmbienteCasaEntity entity) {
        FraseCasaDTO dto = new FraseCasaDTO();
        dto.setId(entity.getId());
        dto.setPronomeId(entity.getPronome().getId());
        dto.setVerboInfinitivoId(entity.getVerboInfinitivo().getId());
        dto.setComplementoId(entity.getComplemento().getId());
        dto.setTempoVerbalId(entity.getTempoVerbal().getId());
        dto.setRespostaCorreta(entity.getRespostaCorreta());

        // Montar a frase
        String tempo = entity.getTempoVerbal().getTempo();
        String pronome = entity.getPronome().getTexto();
        String verbo = entity.getVerboInfinitivo().getVerbo();
        String complemento = entity.getComplemento().getDescricao();
        String pontuacao = entity.getRespostaCorreta();

        String fraseMontada = String.format("(%s) \n%s _______ [%s] %s %s",
                tempo, pronome, verbo, complemento, pontuacao);

        dto.setDescricaoMontada(fraseMontada);
        return dto;
    }
}
