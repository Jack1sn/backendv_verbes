package br.net.villeverbes.service;

import br.net.villeverbes.entity.SeloMedalhaEntity;
import br.net.villeverbes.dto.SeloMedalhaDTO;
import br.net.villeverbes.repository.SeloMedalhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeloMedalhaService {

    @Autowired
    private SeloMedalhaRepository seloMedalhaRepository;

    // Método para buscar selo e medalha por usuarioId
    public SeloMedalhaDTO getSeloMedalhaByUsuarioId(Long usuarioId) {
        Optional<SeloMedalhaEntity> seloMedalhaEntityOptional = seloMedalhaRepository.findByUsuarioJogo_Id(usuarioId);

        // Verifica se o Optional tem valor
        if (seloMedalhaEntityOptional.isPresent()) {
            // Se o valor estiver presente, pega o SeloMedalhaEntity
            SeloMedalhaEntity seloMedalhaEntity = seloMedalhaEntityOptional.get();  // Aqui está a conversão do Optional
            // Converte para DTO
            return new SeloMedalhaDTO(
                    seloMedalhaEntity.getId(),
                    seloMedalhaEntity.getUsuarioJogo() != null ? seloMedalhaEntity.getUsuarioJogo().getId() : null,
                    seloMedalhaEntity.getSeloCasa(),
                    seloMedalhaEntity.getSeloParque(),
                    seloMedalhaEntity.getSeloUniversidade(),
                    seloMedalhaEntity.getMedalha()
            );
        } else {
            // Caso o Optional esteja vazio (não encontrou nada), pode retornar null ou um DTO vazio
            return null;
        }
    }


    public void incrementarMedalha(SeloMedalhaEntity seloMedalha) {
    seloMedalha.setMedalha(seloMedalha.getMedalha() + 1);
    seloMedalhaRepository.save(seloMedalha);
}

}
