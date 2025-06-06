package br.net.villeverbes.service;

import br.net.villeverbes.dto.VerboInfinitivoDTO;
import br.net.villeverbes.entity.VerboInfinitivoEntity;
import br.net.villeverbes.repository.VerboInfinitivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VerboInfinitivoService {

    @Autowired
    private VerboInfinitivoRepository repository;

    /**
     * Lista todos os verbos no formato DTO.
     * 
     * @return Lista de VerboInfinitivoDTO
     */
    public List<VerboInfinitivoDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca um verbo pelo ID e retorna como DTO.
     * 
     * @param id ID do verbo
     * @return Optional com o DTO, se encontrado
     */
    public Optional<VerboInfinitivoDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toDTO);
    }

    /**
     * Cria ou atualiza um verbo.
     * 
     * @param dto DTO com os dados
     * @return DTO salvo
     */
    @Transactional
    public VerboInfinitivoDTO salvar(VerboInfinitivoDTO dto) {
        VerboInfinitivoEntity entity = toEntity(dto);
        VerboInfinitivoEntity salvo = repository.save(entity);
        return toDTO(salvo);
    }

    /**
     * Exclui um verbo pelo ID.
     * 
     * @param id ID do verbo
     * @throws RuntimeException se o verbo não existir
     */
    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Verbo não encontrado para exclusão com ID: " + id);
        }
        repository.deleteById(id);
    }

    // ======================
    // Métodos auxiliares
    // ======================

    /**
     * Converte entidade para DTO.
     */
    private VerboInfinitivoDTO toDTO(VerboInfinitivoEntity entity) {
        VerboInfinitivoDTO dto = new VerboInfinitivoDTO();
        dto.setId(entity.getId());
        dto.setVerbo(entity.getVerbo());
        return dto;
    }

    /**
     * Converte DTO para entidade.
     */
    private VerboInfinitivoEntity toEntity(VerboInfinitivoDTO dto) {
        VerboInfinitivoEntity entity = new VerboInfinitivoEntity();
        entity.setId(dto.getId());
        entity.setVerbo(dto.getVerbo());
        return entity;
    }
}
