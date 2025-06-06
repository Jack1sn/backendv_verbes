package br.net.villeverbes.service;

import br.net.villeverbes.dto.PronomeDTO;
import br.net.villeverbes.entity.PronomeEntity;
import br.net.villeverbes.repository.PronomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PronomeService {

    @Autowired
    private PronomeRepository repository;

    // Listar todos os pronomes
    public List<PronomeDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // Buscar por ID
    public Optional<PronomeDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::converterParaDTO);
    }

    // Criar ou atualizar pronome
    public PronomeDTO salvar(PronomeDTO dto) {
        PronomeEntity entity = converterParaEntity(dto);
        PronomeEntity salvo = repository.save(entity);
        return converterParaDTO(salvo);
    }

    // Deletar por ID
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // Conversão auxiliar: Entity -> DTO
    private PronomeDTO converterParaDTO(PronomeEntity entity) {
        PronomeDTO dto = new PronomeDTO();
        dto.setId(entity.getId());
        dto.setTexto(entity.getTexto());
        return dto;
    }

    // Conversão auxiliar: DTO -> Entity
    private PronomeEntity converterParaEntity(PronomeDTO dto) {
        PronomeEntity entity = new PronomeEntity();
        entity.setId(dto.getId());
        entity.setTexto(dto.getTexto());
        return entity;
    }
}
