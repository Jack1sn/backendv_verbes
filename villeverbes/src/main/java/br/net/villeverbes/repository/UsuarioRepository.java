package br.net.villeverbes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.net.villeverbes.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);

    List<UsuarioEntity> findByPerfil(String perfil);

    List<UsuarioEntity> findByIdAndPerfil(Long id, String perfil);

    Optional<UsuarioEntity> findByPerfilAndIdNot(String perfil, Long id);

    boolean existsByEmail(String email); // correto!
}
