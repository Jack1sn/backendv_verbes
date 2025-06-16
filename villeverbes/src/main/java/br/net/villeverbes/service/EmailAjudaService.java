package br.net.villeverbes.service;

import br.net.villeverbes.dto.EmailAjudaDTO;
import br.net.villeverbes.entity.EmailAjudaEntity;
import br.net.villeverbes.repository.EmailAjudaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailAjudaService {

    private final EmailAjudaRepository emailAjudaRepository;

    public EmailAjudaService(EmailAjudaRepository emailAjudaRepository) {
        this.emailAjudaRepository = emailAjudaRepository;
    }

    // ✅ Grava novo email no banco
    public EmailAjudaDTO enviarEGravar(EmailAjudaDTO dto) {
        validarEntrada(dto);

        String conteudoFormatado = "Assunto: Pedido de ajuda\n\n" +
                "Mensagem:\n" + dto.getMensagem().trim();

        EmailAjudaEntity entity = new EmailAjudaEntity();
        entity.setEmailRemetente(dto.getRemetente().trim());
        entity.setMensagem(conteudoFormatado);
        entity.setDataEnvio(LocalDateTime.now());
        entity.setResposta(null); // ainda sem resposta

        EmailAjudaEntity salvo = emailAjudaRepository.save(entity);
        System.out.println("📥 Email salvo com ID: " + salvo.getId());

        return new EmailAjudaDTO(
            salvo.getEmailRemetente(),
            salvo.getMensagem(),
            salvo.getResposta(),
            salvo.getDataEnvio()
        );
    }

    // ✅ Lista todos os emails do banco
    public List<EmailAjudaDTO> listarTodos() {
        return emailAjudaRepository.findAll().stream()
                .map(e -> new EmailAjudaDTO(
                    e.getEmailRemetente(),
                    e.getMensagem(),
                    e.getResposta(),
                    e.getDataEnvio()
                ))
                .collect(Collectors.toList());
    }

    // ✅ Atualiza ou adiciona uma resposta
    public EmailAjudaDTO responder(Long id, String resposta) {
        Optional<EmailAjudaEntity> optional = emailAjudaRepository.findById(id);

        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Email com ID " + id + " não encontrado.");
        }

        EmailAjudaEntity entity = optional.get();
        entity.setResposta(resposta);
        EmailAjudaEntity atualizado = emailAjudaRepository.save(entity);

        return new EmailAjudaDTO(
            atualizado.getEmailRemetente(),
            atualizado.getMensagem(),
            atualizado.getResposta(),
            atualizado.getDataEnvio()
        );
    }

    private void validarEntrada(EmailAjudaDTO dto) {
        if (dto.getRemetente() == null || dto.getMensagem() == null ||
            dto.getRemetente().isBlank() || dto.getMensagem().isBlank()) {
            throw new IllegalArgumentException("Remetente e mensagem não podem ser nulos ou vazios.");
        }

    }

 public boolean temMensagensNaoRespondidas() {
    return emailAjudaRepository.existsByRespostaIsNullOrRespostaEquals("");
}

public Long contarNaoRespondidas() {
        return emailAjudaRepository.countByRespostaIsNullOrRespostaEquals("");
    }


}
