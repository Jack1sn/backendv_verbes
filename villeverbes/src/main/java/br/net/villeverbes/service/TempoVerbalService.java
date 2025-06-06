package br.net.villeverbes.service;

import br.net.villeverbes.dto.TempoVerbalDTO;
import br.net.villeverbes.entity.TempoVerbalEntity;
import br.net.villeverbes.repository.TempoVerbalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TempoVerbalService {

    private final TempoVerbalRepository repository;

    /**
     * Lista todos os tempos verbais disponíveis.
     */
    public List<TempoVerbalDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca um tempo verbal pelo ID.
     */
    public Optional<TempoVerbalDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::converterParaDTO);
    }

    /**
     * Salva ou atualiza um tempo verbal.
     */
    @Transactional
    public TempoVerbalDTO salvar(TempoVerbalDTO dto) {
        TempoVerbalEntity entity = converterParaEntity(dto);
        TempoVerbalEntity salvo = repository.save(entity);
        return converterParaDTO(salvo);
    }

    /**
     * Deleta um tempo verbal pelo ID, se existir.
     */
    @Transactional
    public void deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Tempo verbal com ID " + id + " não encontrado.");
        }
    }

    // Conversão interna para DTO
    private TempoVerbalDTO converterParaDTO(TempoVerbalEntity entity) {
        TempoVerbalDTO dto = new TempoVerbalDTO();
        dto.setId(entity.getId());
        dto.setTempo(entity.getTempo());
        return dto;
    }

    // Conversão interna para Entity
    private TempoVerbalEntity converterParaEntity(TempoVerbalDTO dto) {
        TempoVerbalEntity entity = new TempoVerbalEntity();
        entity.setId(dto.getId());
        entity.setTempo(dto.getTempo());
        return entity;
    }
}
