package br.net.villeverbes.repository;

import br.net.villeverbes.entity.UsuarioEntity;
import br.net.villeverbes.entity.UsuarioJogoEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJogoRepository extends JpaRepository<UsuarioJogoEntity, Long> {
 Optional<List<UsuarioJogoEntity>> findByUsuario(UsuarioEntity usuario);
}
