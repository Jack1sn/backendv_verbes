package br.net.villeverbes.repository;

import br.net.villeverbes.entity.VerboInfinitivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerboInfinitivoRepository extends JpaRepository<VerboInfinitivoEntity, Long> {
    Optional<VerboInfinitivoEntity> findByVerbo(String verbo);
}
