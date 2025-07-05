package br.net.villeverbes.repository;

import br.net.villeverbes.entity.SeloMedalhaEntity;
import br.net.villeverbes.entity.UsuarioJogoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeloMedalhaRepository extends JpaRepository<SeloMedalhaEntity, Long> {

    Optional<SeloMedalhaEntity> findByUsuarioJogo(UsuarioJogoEntity usuarioJogo);

    List<SeloMedalhaEntity> findByUsuarioJogo_Id(Long usuarioJogoId);


}
