package br.net.villeverbes.repository;

import br.net.villeverbes.entity.SeloMedalhaEntity;
import br.net.villeverbes.entity.UsuarioJogoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeloMedalhaRepository extends JpaRepository<SeloMedalhaEntity, Long> {

    @EntityGraph(attributePaths = {"usuarioJogo"})
    Optional<SeloMedalhaEntity> findByUsuarioJogo(UsuarioJogoEntity usuarioJogo);

    @EntityGraph(attributePaths = {"usuarioJogo"})
    List<SeloMedalhaEntity> findByUsuarioJogo_Id(Long usuarioJogoId);

    @EntityGraph(attributePaths = {"usuarioJogo"})
    @Query("SELECT sm FROM SeloMedalhaEntity sm WHERE sm.usuario.id = :usuarioId ORDER BY sm.medalha DESC")
    List<SeloMedalhaEntity> findByUsuarioIdOrderedByMedalha(Long usuarioId);

    @EntityGraph(attributePaths = {"usuarioJogo"})
    @Query("SELECT sm FROM SeloMedalhaEntity sm WHERE sm.personagem = :personagem ORDER BY sm.medalha DESC")
    List<SeloMedalhaEntity> findByPersonagemOrderedByMedalha(String personagem);
}
