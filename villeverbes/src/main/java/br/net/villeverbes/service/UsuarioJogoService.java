package br.net.villeverbes.service;

import br.net.villeverbes.dto.UsuarioJogoDTO;
import br.net.villeverbes.entity.UsuarioEntity;
import br.net.villeverbes.entity.UsuarioJogoEntity;
import br.net.villeverbes.repository.UsuarioJogoRepository;
import br.net.villeverbes.repository.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
//import java.util.Optional;

@Service
public class UsuarioJogoService {

    private final UsuarioJogoRepository usuarioJogoRepository;
    private final UsuarioRepository usuarioRepository;


    public UsuarioJogoService(UsuarioJogoRepository usuarioJogoRepository, UsuarioRepository usuarioRepository) {
        this.usuarioJogoRepository = usuarioJogoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Cria um novo registro de jogo para o usuário.
     * Agora, utilizando o DTO.
     */
    public UsuarioJogoEntity criarJogo(Long usuarioId, UsuarioJogoDTO jogoDTO) {
        // Buscar usuário pelo ID
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Criar o novo registro de jogo a partir do DTO
        UsuarioJogoEntity usuarioJogo = new UsuarioJogoEntity();
        usuarioJogo.setUsuario(usuario);
        usuarioJogo.setPersonagem(jogoDTO.getPersonagem());
        usuarioJogo.setAmbiente(jogoDTO.getAmbiente());
        usuarioJogo.setAcertos(jogoDTO.getAcertos());
        usuarioJogo.setTotal(jogoDTO.getTotal());
        usuarioJogo.setAcertoPorAmbiente(jogoDTO.getAcertoPorAmbiente());
        usuarioJogo.setData(LocalDate.now());  // Data do jogo
        usuarioJogo.setResultado(jogoDTO.isResultado());

        // Salvar no banco de dados
        return usuarioJogoRepository.save(usuarioJogo);
    }

    /**
     * Recupera todos os registros de jogos de um usuário.
     */
    public List<UsuarioJogoEntity> listarJogosPorUsuario(Long usuarioId) {
        // Buscar o usuário pelo ID
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Retornar os jogos do usuário
        return usuarioJogoRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Nenhum jogo encontrado para este usuário."));
    }

    /**
     * Recupera o primeiro jogo específico de um usuário.
     */
    public UsuarioJogoEntity obterJogoPorId(Long usuarioId, Long jogoId) {
        // Buscar o usuário pelo ID
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Buscar o jogo específico para o usuário pelo ID do jogo
        return usuarioJogoRepository.findById(jogoId)
                .filter(jogo -> jogo.getUsuario().equals(usuario))  // Verificar se o jogo pertence ao usuário
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado ou não pertence a este usuário."));
    }
}
