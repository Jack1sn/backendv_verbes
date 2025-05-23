package br.net.villeverbes.controller;


import br.net.villeverbes.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // Enviar e-mail com nome, e-mail e senha (POST)
    @PostMapping("/enviar")
    public String enviarEmailComSenha(
        @RequestParam String email,
        @RequestParam(required = false) String nome,
        @RequestParam String senha
    ) {
        emailService.sendEmail(email, nome, senha);
        return "E-mail enviado com sucesso para " + email;
    }

    // Enviar e-mail livre (POST)
    @PostMapping("/simples")
    public String enviarEmailSimples(
        @RequestParam String para,
        @RequestParam String assunto,
        @RequestParam String texto
    ) {
        emailService.enviarEmail(para, assunto, texto);
        return "E-mail simples enviado com sucesso para " + para;
    }
}
