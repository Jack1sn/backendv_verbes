
package br.net.villeverbes.repository;

import br.net.villeverbes.entity.SeloMedalhaEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeloMedalhaRepository extends JpaRepository<SeloMedalhaEntity, Long> {

    Optional<SeloMedalhaEntity> findByUsuarioJogo_Id(Long usuarioId);
}

  

