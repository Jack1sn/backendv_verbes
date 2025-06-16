package br.net.villeverbes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.net.villeverbes.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    // Buscar por e-mail (usado para login e validação de cadastro)
    Optional<UsuarioEntity> findByEmail(String email);

    // Verificar se já existe um e-mail cadastrado
    boolean existsByEmail(String email);

    // Listar todos os usuários de um determinado perfil
    List<UsuarioEntity> findByPerfil(String perfil);

    // Buscar um usuário pelo ID e perfil (útil para autorização e validação)
    Optional<UsuarioEntity> findByIdAndPerfil(Long id, String perfil);

    // Buscar todos os usuários exceto um específico de um perfil
    List<UsuarioEntity> findByPerfilAndIdNot(String perfil, Long id);

    // Buscar por login (caso use login separado do e-mail)
    Optional<UsuarioEntity> findByLogin(String login);


    // Buscar usuário ativo por e-mail (caso tenha campo ativo no banco futuramente)
    // Optional<UsuarioEntity> findByEmailAndAtivoTrue(String email);

    // Correto: retorna jogadores ativos
        List<UsuarioEntity> findByPerfilAndAtivoTrue(String perfil);


}
