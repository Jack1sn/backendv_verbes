package br.net.villeverbes.controller;



import br.net.villeverbes.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private EmailService emailService;

    // Função para gerar uma senha aleatória de 6 caracteres
    private String gerarSenhaAleatoria() {
        SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder();
        String caracteres = "JV0123456789";

        for (int i = 0; i < 5; i++) {
            int indice = random.nextInt(caracteres.length());
            senha.append(caracteres.charAt(indice));
        }
        return senha.toString();
    }

    // Endpoint para enviar a senha por e-mail
    @PostMapping("/enviar-senha")
    public String enviarSenha(@RequestParam String email) {
        String senha = gerarSenhaAleatoria();
        String assunto = "Sua nova senha para accessar ville des verbes";
        String corpo = "Sua nova senha é: " + senha;

        emailService.enviarEmail(email, assunto, corpo);
        return "Senha enviada para o e-mail " + email;
    }
}
