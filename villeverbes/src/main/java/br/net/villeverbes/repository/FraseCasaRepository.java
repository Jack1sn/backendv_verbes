package br.net.villeverbes.repository;

import br.net.villeverbes.entity.FraseAmbienteCasaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FraseCasaRepository extends JpaRepository<FraseAmbienteCasaEntity, Long> {

    @EntityGraph(attributePaths = {
        "pronome", "verboInfinitivo", "complemento", "tempo"
    })
    List<FraseAmbienteCasaEntity> findAll();
}
