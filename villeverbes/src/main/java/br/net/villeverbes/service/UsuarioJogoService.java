package br.net.villeverbes.service;

import br.net.villeverbes.dto.UsuarioJogoDTO;
import br.net.villeverbes.entity.SeloMedalhaEntity;
import br.net.villeverbes.entity.UsuarioEntity;
import br.net.villeverbes.entity.UsuarioJogoEntity;
import br.net.villeverbes.repository.SeloMedalhaRepository;
import br.net.villeverbes.repository.UsuarioJogoRepository;
import br.net.villeverbes.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        usuarioJogo.setAcertosCasa(jogoDTO.getAcertosCasa());
        usuarioJogo.setAcertosParque(jogoDTO.getAcertosParque());
        usuarioJogo.setAcertosUniversidade(jogoDTO.getAcertosUniversidade());

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
        usuarioJogo.setAcertosCasa(jogoDTO.getAcertosCasa());
        usuarioJogo.setAcertosParque(jogoDTO.getAcertosParque());
        usuarioJogo.setAcertosUniversidade(jogoDTO.getAcertosUniversidade());

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
        if (jogoDTO.getAcertosCasa() < 0 || jogoDTO.getAcertosParque() < 0 || jogoDTO.getAcertosUniversidade() < 0) {
            throw new IllegalArgumentException("Acertos não podem ser valores negativos.");
        }
    }

    /**
     * Calcula o total de acertos a partir do DTO.
     */
    private int calcularTotalAcertos(UsuarioJogoDTO jogoDTO) {
        return jogoDTO.getAcertosCasa() + jogoDTO.getAcertosParque() + jogoDTO.getAcertosUniversidade();
    }

    /**
     * Busca o usuário pelo ID e verifica se existe.
     */
    private UsuarioEntity buscarUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));  // Exceção simples
    }

public void criarVariosJogos(Long usuarioId, List<UsuarioJogoDTO> jogosDTO) {
    UsuarioEntity usuario = buscarUsuario(usuarioId);

    for (UsuarioJogoDTO jogoDTO : jogosDTO) {
        validarJogoDTO(jogoDTO);

        UsuarioJogoEntity usuarioJogo = new UsuarioJogoEntity();
        usuarioJogo.setUsuario(usuario);
        usuarioJogo.setPersonagem(jogoDTO.getPersonagem());
        usuarioJogo.setAcertosCasa(jogoDTO.getAcertosCasa());
        usuarioJogo.setAcertosParque(jogoDTO.getAcertosParque());
        usuarioJogo.setAcertosUniversidade(jogoDTO.getAcertosUniversidade());
        usuarioJogo.setTotalAcertos(calcularTotalAcertos(jogoDTO));
        usuarioJogo.setData(LocalDate.now());

        // método que contém a lógica dos selos
        salvarComSelo(usuarioJogo);
    }
}

@Autowired
private SeloMedalhaRepository seloMedalhaRepository;



public void salvarComSelo(UsuarioJogoEntity jogoEntity) {
    // Salva o jogo primeiro e obtém o ID
    UsuarioJogoEntity jogoSalvo = usuarioJogoRepository.save(jogoEntity);

    boolean casa11 = jogoSalvo.getAcertosCasa() == 11;
    boolean parque11 = jogoSalvo.getAcertosParque() == 11;
    boolean universidade11 = jogoSalvo.getAcertosUniversidade() == 11;
    boolean ganhouMedalha = casa11 && parque11 && universidade11;

    // Agora o jogoSalvo tem ID, pode buscar corretamente
    Optional<SeloMedalhaEntity> optionalSelo = seloMedalhaRepository.findByUsuarioJogo(jogoEntity);


    SeloMedalhaEntity selo = optionalSelo.orElseGet(() -> new SeloMedalhaEntity(jogoSalvo));

    if (casa11) selo.setSeloCasa(selo.getSeloCasa() + 1);
    if (parque11) selo.setSeloParque(selo.getSeloParque() + 1);
    if (universidade11) selo.setSeloUniversidade(selo.getSeloUniversidade() + 1);
    if (ganhouMedalha) selo.setMedalha(selo.getMedalha() + 1);

    seloMedalhaRepository.save(selo);
}



}
