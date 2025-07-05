package br.net.villeverbes.service;

import br.net.villeverbes.dto.SeloMedalhaDTO;
import br.net.villeverbes.entity.SeloMedalhaEntity;
import br.net.villeverbes.entity.UsuarioJogoEntity;
import br.net.villeverbes.repository.SeloMedalhaRepository;
import br.net.villeverbes.repository.UsuarioJogoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeloMedalhaService {

    @Autowired
    private SeloMedalhaRepository seloMedalhaRepository;

    @Autowired
    private UsuarioJogoRepository usuarioJogoRepository;

    /**
     * Retorna o resumo de selo/medalha de um jogo específico (um único registro)
     */
    public SeloMedalhaDTO getSeloMedalhaByUsuarioId(Long usuarioJogoId) {
        Optional<UsuarioJogoEntity> usuarioJogoOpt = usuarioJogoRepository.findById(usuarioJogoId);

        if (usuarioJogoOpt.isEmpty()) {
            return null;
        }

        Optional<SeloMedalhaEntity> seloOpt = seloMedalhaRepository.findByUsuarioJogo(usuarioJogoOpt.get());

        return seloOpt.map(selo -> new SeloMedalhaDTO(
                selo.getId(),
                usuarioJogoOpt.get().getId(),
                selo.getSeloCasa(),
                selo.getSeloParque(),
                selo.getSeloUniversidade(),
                selo.getMedalha()
        )).orElse(null);
    }

    /**
     * Retorna todos os selos do jogador (caso tenha múltiplos jogos/sessões)
     */
    public List<SeloMedalhaDTO> getSelosByUsuarioId(Long usuarioId) {
        List<SeloMedalhaEntity> selos = seloMedalhaRepository.findByUsuarioJogo_Id(usuarioId);

        return selos.stream().map(selo -> new SeloMedalhaDTO(
                selo.getId(),
                selo.getUsuarioJogo().getId(),
                selo.getSeloCasa(),
                selo.getSeloParque(),
                selo.getSeloUniversidade(),
                selo.getMedalha()
        )).collect(Collectors.toList());
    }

    /**
     * Incrementa o número de medalhas do jogador
     */
    public void incrementarMedalha(SeloMedalhaEntity seloMedalha) {
        seloMedalha.setMedalha(seloMedalha.getMedalha() + 1);
        seloMedalhaRepository.save(seloMedalha);
    }
}
