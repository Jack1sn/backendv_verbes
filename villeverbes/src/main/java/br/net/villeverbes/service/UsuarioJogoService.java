package br.net.villeverbes.service;

import br.net.villeverbes.dto.UsuarioJogoDTO;
import br.net.villeverbes.entity.UsuarioEntity;
import br.net.villeverbes.entity.UsuarioJogoEntity;
import br.net.villeverbes.repository.UsuarioJogoRepository;
import br.net.villeverbes.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioJogoService {

    private final UsuarioJogoRepository usuarioJogoRepository;
    private final UsuarioRepository usuarioRepository;

    // Construtor para injeção de dependência
    public UsuarioJogoService(UsuarioJogoRepository usuarioJogoRepository, UsuarioRepository usuarioRepository) {
        this.usuarioJogoRepository = usuarioJogoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Cria um novo registro de jogo para o usuário.
     */
    public UsuarioJogoEntity criarJogo(Long usuarioId, UsuarioJogoDTO jogoDTO) {
        UsuarioEntity usuario = buscarUsuario(usuarioId);  // Valida a existência do usuário
        validarJogoDTO(jogoDTO);  // Valida os dados do jogo DTO

        // Criando um novo objeto de jogo
        UsuarioJogoEntity usuarioJogo = new UsuarioJogoEntity();
        usuarioJogo.setUsuario(usuario);
        usuarioJogo.setPersonagem(jogoDTO.getPersonagem());
        usuarioJogo.setAcertoCasa(jogoDTO.getAcertoCasa());
        usuarioJogo.setAcertoParque(jogoDTO.getAcertoParque());
        usuarioJogo.setAcertoUniversidade(jogoDTO.getAcertoUniversidade());

        // Calculando o total de acertos
        usuarioJogo.setTotalAcertos(calcularTotalAcertos(jogoDTO));

        // Data do jogo
        usuarioJogo.setData(LocalDate.now());

        // Salvar no banco de dados
        return usuarioJogoRepository.save(usuarioJogo);
    }

    /**
     * Recupera todos os registros de jogos de um usuário.
     */
    public List<UsuarioJogoEntity> listarJogosPorUsuario(Long usuarioId) {
        buscarUsuario(usuarioId);  // Valida a existência do usuário

        List<UsuarioJogoEntity> jogos = usuarioJogoRepository.findByUsuarioId(usuarioId);
        if (jogos.isEmpty()) {
            throw new RuntimeException("Jogos não encontrados para o usuário.");  // Erro simples, sem exceção personalizada
        }

        return jogos;
    }

    /**
     * Recupera um jogo específico de um usuário.
     */
    public UsuarioJogoEntity obterJogoPorId(Long usuarioId, Long jogoId) {
        return usuarioJogoRepository.findById(jogoId)
                .filter(jogo -> jogo.getUsuario().getId().equals(usuarioId))  // Verifica se o jogo pertence ao usuário
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado ou não pertence a este usuário."));  // Erro simples
    }

    /**
     * Atualiza um jogo específico de um usuário.
     */
    public UsuarioJogoEntity atualizarJogo(Long usuarioId, Long jogoId, UsuarioJogoDTO jogoDTO) {
        buscarUsuario(usuarioId);  // Valida a existência do usuário

        UsuarioJogoEntity usuarioJogo = obterJogoPorId(usuarioId, jogoId);  // Busca o jogo a ser atualizado
        validarJogoDTO(jogoDTO);  // Valida os dados do DTO

        // Atualiza os campos do jogo
        usuarioJogo.setPersonagem(jogoDTO.getPersonagem());
        usuarioJogo.setAcertoCasa(jogoDTO.getAcertoCasa());
        usuarioJogo.setAcertoParque(jogoDTO.getAcertoParque());
        usuarioJogo.setAcertoUniversidade(jogoDTO.getAcertoUniversidade());

        // Calculando o total de acertos
        usuarioJogo.setTotalAcertos(calcularTotalAcertos(jogoDTO));

        // Atualizando a data do jogo
        usuarioJogo.setData(LocalDate.now());

        return usuarioJogoRepository.save(usuarioJogo);
    }

    /**
     * Deleta um jogo específico de um usuário.
     */
    public void deletarJogo(Long usuarioId, Long jogoId) {
        buscarUsuario(usuarioId);  // Valida a existência do usuário

        UsuarioJogoEntity usuarioJogo = obterJogoPorId(usuarioId, jogoId);  // Busca o jogo a ser deletado

        usuarioJogoRepository.delete(usuarioJogo);  // Deleta o jogo
    }

    /**
     * Valida os dados do DTO.
     */
    private void validarJogoDTO(UsuarioJogoDTO jogoDTO) {
        if (jogoDTO.getAcertoCasa() < 0 || jogoDTO.getAcertoParque() < 0 || jogoDTO.getAcertoUniversidade() < 0) {
            throw new IllegalArgumentException("Acertos não podem ser valores negativos.");
        }
    }

    /**
     * Calcula o total de acertos a partir do DTO.
     */
    private int calcularTotalAcertos(UsuarioJogoDTO jogoDTO) {
        return jogoDTO.getAcertoCasa() + jogoDTO.getAcertoParque() + jogoDTO.getAcertoUniversidade();
    }

    /**
     * Busca o usuário pelo ID e verifica se existe.
     */
    private UsuarioEntity buscarUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));  // Exceção simples
    }
}
