package br.net.villeverbes.service;

import br.net.villeverbes.dto.EmailAjudaDTO;
import br.net.villeverbes.entity.EmailAjudaEntity;
import br.net.villeverbes.repository.EmailAjudaRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailAjudaService {

    private final EmailAjudaRepository emailAjudaRepository;
    private final JavaMailSender mailSender;

    public EmailAjudaService(EmailAjudaRepository emailAjudaRepository,
                              JavaMailSender mailSender) {
        this.emailAjudaRepository = emailAjudaRepository;
        this.mailSender = mailSender;
    }

    // ✅ Envia email e grava no banco
    public EmailAjudaDTO enviarEGravar(EmailAjudaDTO dto) {
        validarEntrada(dto);

        String conteudoFormatado = "Assunto: Pedido de ajuda\n\n" +
                "Mensagem:\n" + dto.getMensagem().trim();

        try {
            MimeMessage mensagemEmail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagemEmail, true);
            helper.setTo("villedesverbes@gmail.com");
            helper.setSubject("Pedido de Ajuda");
            helper.setText(conteudoFormatado, false);
            helper.setFrom(dto.getRemetente().trim());

            mailSender.send(mensagemEmail);
            System.out.println("📨 Email enviado com sucesso!");

        } catch (MessagingException e) {
            System.err.println("❌ Erro ao enviar email: " + e.getMessage());
            throw new RuntimeException("Erro ao enviar email.", e);
        }

        EmailAjudaEntity entity = new EmailAjudaEntity();
        entity.setEmailRemetente(dto.getRemetente().trim());
        entity.setMensagem(conteudoFormatado);
        entity.setDataEnvio(LocalDateTime.now());
        entity.setResposta(null);

        EmailAjudaEntity salvo = emailAjudaRepository.save(entity);
        System.out.println("📥 Email salvo no banco com ID: " + salvo.getId());

        return new EmailAjudaDTO(
            salvo.getId(),
            salvo.getEmailRemetente(),
            salvo.getMensagem(),
            salvo.getResposta(),
            salvo.getDataEnvio()
        );
    }

    // ✅ Listar todos os emails
    public List<EmailAjudaDTO> listarTodos() {
        return emailAjudaRepository.findAll().stream()
                .map(e -> new EmailAjudaDTO(
                        e.getId(),
                        e.getEmailRemetente(),
                        e.getMensagem(),
                        e.getResposta(),
                        e.getDataEnvio()
                ))
                .collect(Collectors.toList());
    }

    // ✅ Responder um email específico e enviar por email também
    public EmailAjudaDTO responder(Long id, String resposta) {
        Optional<EmailAjudaEntity> optional = emailAjudaRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Email com ID " + id + " não encontrado.");
        }

        EmailAjudaEntity entity = optional.get();
        entity.setResposta(resposta);
        EmailAjudaEntity atualizado = emailAjudaRepository.save(entity);

        // ✅ Enviar a resposta por e-mail ao remetente
        try {
            MimeMessage mensagemEmail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagemEmail, true);
            helper.setTo(entity.getEmailRemetente());
            helper.setSubject("Resposta à sua solicitação de ajuda");

            String corpoHtml = String.format(
                "<html><body>" +
                    "<p>Olá,</p>" +
                    "<p>Recebemos sua solicitação e aqui está nossa resposta:</p>" +
                    "<blockquote style='border-left: 4px solid #ccc; margin: 10px 0; padding-left: 10px;'>%s</blockquote>" +
                    "<p>Se precisar de mais assistência, estamos à disposição.</p>" +
                    "<br><p>Atenciosamente,<br>Equipe Ville de Verbes</p>" +
                "</body></html>", resposta);

            helper.setText(corpoHtml, true);
            helper.setFrom("villedesverbes@gmail.com");

            mailSender.send(mensagemEmail);
            System.out.println("📨 Resposta enviada ao remetente: " + entity.getEmailRemetente());

        } catch (MessagingException e) {
            System.err.println("❌ Erro ao enviar a resposta por email: " + e.getMessage());
            // Não lança exceção porque a resposta foi salva com sucesso
        }

        return new EmailAjudaDTO(
                atualizado.getId(),
                atualizado.getEmailRemetente(),
                atualizado.getMensagem(),
                atualizado.getResposta(),
                atualizado.getDataEnvio()
        );
    }

    // ✅ Verificar se há mensagens não respondidas
    public boolean temMensagensNaoRespondidas() {
        return emailAjudaRepository.existsByRespostaIsNullOrRespostaEquals("");
    }

    // ✅ Contar mensagens não respondidas
    public Long contarNaoRespondidas() {
        return emailAjudaRepository.countByRespostaIsNullOrRespostaEquals("");
    }

    // ✅ Validação dos dados de entrada
    private void validarEntrada(EmailAjudaDTO dto) {
        if (dto.getRemetente() == null || dto.getMensagem() == null ||
                dto.getRemetente().isBlank() || dto.getMensagem().isBlank()) {
            throw new IllegalArgumentException("Remetente e mensagem não podem ser nulos ou vazios.");
        }
    }
}
