package br.net.villeverbes.service;

import br.net.villeverbes.dto.SeloMedalhaDTO;
import br.net.villeverbes.dto.TrofeuDTO;
import br.net.villeverbes.entity.SeloMedalhaEntity;
import br.net.villeverbes.entity.UsuarioJogoEntity;
import br.net.villeverbes.repository.SeloMedalhaRepository;
import br.net.villeverbes.repository.UsuarioJogoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeloMedalhaService {

    @Autowired
    private SeloMedalhaRepository seloMedalhaRepository;

    @Autowired
    private UsuarioJogoRepository usuarioJogoRepository;

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
                selo.getMedalha(),
                selo.getPosicao(),
                usuarioJogoOpt.get().getPersonagem() // ADICIONADO
        )).orElse(null);
    }

    public List<SeloMedalhaDTO> getSelosByUsuarioId(Long usuarioId) {
        List<SeloMedalhaEntity> selos = seloMedalhaRepository.findByUsuarioJogo_Id(usuarioId);

        selos.sort((s1, s2) -> Integer.compare(s2.getMedalha(), s1.getMedalha()));

        for (int i = 0; i < selos.size(); i++) {
            selos.get(i).setPosicao(i + 1);
        }

        return selos.stream().map(selo -> {
            UsuarioJogoEntity usuario = selo.getUsuarioJogo();
            return new SeloMedalhaDTO(
                    selo.getId(),
                    usuario.getId(),
                    selo.getSeloCasa(),
                    selo.getSeloParque(),
                    selo.getSeloUniversidade(),
                    selo.getMedalha(),
                    selo.getPosicao(),
                    usuario.getPersonagem() // ADICIONADO
            );
        }).collect(Collectors.toList());
    }
        
    public List<SeloMedalhaDTO> getSelosByPersonagem(String personagem) {
        List<SeloMedalhaEntity> selos = seloMedalhaRepository.findByPersonagemOrderedByMedalha(personagem);

        selos.sort((s1, s2) -> Integer.compare(s2.getMedalha(), s1.getMedalha()));

        for (int i = 0; i < selos.size(); i++) {
            selos.get(i).setPosicao(i + 1);
        }

        return selos.stream().map(selo -> {
            UsuarioJogoEntity usuario = selo.getUsuarioJogo();
            return new SeloMedalhaDTO(
                    selo.getId(),
                    usuario.getId(),
                    selo.getSeloCasa(),
                    selo.getSeloParque(),
                    selo.getSeloUniversidade(),
                    selo.getMedalha(),
                    selo.getPosicao(),
                    usuario.getPersonagem() // ADICIONADO
            );
        }).collect(Collectors.toList());
    }

    public void incrementarMedalha(SeloMedalhaEntity seloMedalha) {
        seloMedalha.setMedalha(seloMedalha.getMedalha() + 1);
        seloMedalhaRepository.save(seloMedalha);
    }


    
    @Transactional(readOnly = true)
    public List<SeloMedalhaDTO> getRankingCompleto() {
        List<SeloMedalhaEntity> selos = seloMedalhaRepository.findAll();

        selos.sort((s1, s2) -> Integer.compare(s2.getMedalha(), s1.getMedalha()));

        for (int i = 0; i < selos.size(); i++) {
            selos.get(i).setPosicao(i + 1);
        }

        return selos.stream().map(selo -> {
            UsuarioJogoEntity usuario = selo.getUsuarioJogo();
            return new SeloMedalhaDTO(
                    selo.getId(),
                    usuario.getId(),
                    selo.getSeloCasa(),
                    selo.getSeloParque(),
                    selo.getSeloUniversidade(),
                    selo.getMedalha(),
                    selo.getPosicao(),
                    usuario.getPersonagem() // ADICIONADO
            );
        }).collect(Collectors.toList());
    }

    public List<TrofeuDTO> getTrofeusDoUsuario(Long usuarioId) {
    List<SeloMedalhaEntity> selos = seloMedalhaRepository. findByUsuarioJogo_Id(usuarioId);

    List<TrofeuDTO> trofeus = new ArrayList<>();

    for (SeloMedalhaEntity s : selos) {
        String selo = "";
        if (s.getSeloCasa() != null) {
            selo = "Maison";
        } else if (s.getSeloParque() != null) {
            selo = "Place";
        } else if (s.getSeloUniversidade() != null) {
            selo = "Université";
        }

        int quantidade = 1; // Pode ajustar se tiver um campo de quantidade

        // Converte o Integer medalha em emoji ou vazio
        String medalha = (s.getMedalha() != null && s.getMedalha() == 1) ? "🥇" : "";

        trofeus.add(new TrofeuDTO(selo, quantidade, medalha));
    }

    return trofeus;
}

}
