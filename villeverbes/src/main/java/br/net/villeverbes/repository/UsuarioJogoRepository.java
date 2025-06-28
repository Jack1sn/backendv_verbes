package br.net.villeverbes.repository;

import br.net.villeverbes.entity.UsuarioEntity;
import br.net.villeverbes.entity.UsuarioJogoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioJogoRepository extends JpaRepository<UsuarioJogoEntity, Long> {

    // Recupera todos os jogos de um usuário
    List<UsuarioJogoEntity> findByUsuario(UsuarioEntity usuario);

    // Recupera todos os jogos de um usuário usando o ID do usuário
    List<UsuarioJogoEntity> findByUsuarioId(Long usuarioId);  // Alterado de Optional<List<UsuarioJogoEntity>> para List<UsuarioJogoEntity>

    // Recupera os selos e medalhas de um jogo específico, usando o ID do jogo
  
   
       List<UsuarioJogoEntity> findByUsuario_Id(Long usuarioId);
}
