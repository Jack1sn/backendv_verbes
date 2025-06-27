package br.net.villeverbes.repository;

import br.net.villeverbes.entity.TempoVerbalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TempoVerbalRepository extends JpaRepository<TempoVerbalEntity, Long> {
    Optional<TempoVerbalEntity> findByTempo(String tempo);
     
}
