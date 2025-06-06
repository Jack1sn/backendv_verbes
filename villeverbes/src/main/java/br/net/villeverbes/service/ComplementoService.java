
package br.net.villeverbes.service;

import br.net.villeverbes.dto.ComplementoDTO;
import br.net.villeverbes.entity.ComplementoEntity;
import br.net.villeverbes.repository.ComplementoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComplementoService {

    @Autowired
    private ComplementoRepository repository;

    // Listar todos os complementos
    public List<ComplementoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // Buscar por ID
    public Optional<ComplementoDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::converterParaDTO);
    }

    // Salvar ou atualizar
    public ComplementoDTO salvar(ComplementoDTO dto) {
        ComplementoEntity entity = converterParaEntity(dto);
        ComplementoEntity salvo = repository.save(entity);
        return converterParaDTO(salvo);
    }

    // Deletar por ID
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // Conversão auxiliar: Entity -> DTO
    private ComplementoDTO converterParaDTO(ComplementoEntity entity) {
        ComplementoDTO dto = new ComplementoDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }

    // Conversão auxiliar: DTO -> Entity
    private ComplementoEntity converterParaEntity(ComplementoDTO dto) {
        ComplementoEntity entity = new ComplementoEntity();
        entity.setId(dto.getId());
        entity.setDescricao(dto.getDescricao());
        return entity;
    }
}
