package br.net.villeverbes.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private static final String REMETENTE_EMAIL = "villedesverbes@gmail.com"; // Use o mesmo e-mail da autenticação SMTP
    private static final String REMETENTE_NOME = "Ville de Verbes";

    // Envia e-mail simples com HTML
    public void enviarEmail(String para, String assunto, String corpoHtml) {
        enviarEmail(para, assunto, corpoHtml, null, null, null);
    }

    // Envia e-mail HTML com CC, BCC e anexo
    public void enviarEmail(String para, String assunto, String corpoHtml,
                            String[] cc, String[] bcc, String caminhoAnexo) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // Define o remetente — Tratando a exceção UnsupportedEncodingException
            try {
                helper.setFrom(new InternetAddress(REMETENTE_EMAIL, REMETENTE_NOME));
            } catch (UnsupportedEncodingException e) {
                logger.error("Erro ao definir o remetente: {}", e.getMessage());
                throw new MessagingException("Erro ao definir o remetente", e);
            }

            helper.setTo(para);
            helper.setSubject(assunto);
            helper.setText(corpoHtml, true); // true = conteúdo HTML

            if (cc != null && cc.length > 0) helper.setCc(cc);
            if (bcc != null && bcc.length > 0) helper.setBcc(bcc);

            if (caminhoAnexo != null && !caminhoAnexo.isEmpty()) {
                FileSystemResource file = new FileSystemResource(new File(caminhoAnexo));
                helper.addAttachment(file.getFilename(), file);
            }

            emailSender.send(message);
            logger.info("E-mail enviado com sucesso para {}", para);

        } catch (MessagingException | MailException e) {
            logger.error("Erro ao enviar e-mail: {}", e.getMessage());
        }
    }

    // E-mail de boas-vindas
    public void sendEmail(String email, String nome, String senha) {
        String assunto = "Acesso ao sistema Ville Verbes";

        String corpoHtml = String.format(
            "<html><body>" +
                "<h2>Olá%s,</h2>" +
                "<p>Seu acesso ao sistema <strong>Ville Verbes</strong> foi criado com sucesso!</p>" +
                "<p><strong>Login:</strong> %s<br/>" +
                "<strong>Senha:</strong> %s</p>" +
                "<p style='color:red;'>Recomendamos alterar sua senha após o primeiro acesso.</p>" +
                "<hr/><p>Atenciosamente,<br/>Equipe Ville de Verbes</p>" +
            "</body></html>",
            (nome != null && !nome.isEmpty() ? " " + nome : ""),
            email,
            senha
        );

        enviarEmail(email, assunto, corpoHtml);
    }
}
