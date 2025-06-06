package br.net.villeverbes.controller;

import br.net.villeverbes.dto.LoginDTO;
import br.net.villeverbes.dto.LoginResponseDTO;
import br.net.villeverbes.dto.UsuarioDTO;
import br.net.villeverbes.entity.UsuarioEntity;
import br.net.villeverbes.repository.UsuarioRepository;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private static final String SECRET_KEY = "meusegredoaqui";
    private static final long EXPIRATION_TIME = 86400000; // 1 dia em milissegundos

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {
        // Busca o usuário pelo e-mail
        UsuarioEntity usuario = usuarioRepository.findByEmail(loginRequest.getEmail()).orElse(null);

        // Verifica se o usuário existe
        if (usuario == null) {
            return ResponseEntity.status(401).body("E-mail ou senha incorretos.");
        }

        // Valida a senha
        boolean senhaValida = passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha());
        if (!senhaValida) {
            return ResponseEntity.status(401).body("E-mail ou senha incorretos.");
        }

        // Gera o token JWT
        String token = JWT.create()
                .withSubject(usuario.getEmail())
                .withIssuer("villeverbesAPI") 
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));

        // Constrói o DTO de resposta com token e dados do usuário
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        LoginResponseDTO response = new LoginResponseDTO(token, usuarioDTO);

        return ResponseEntity.ok(response);
    }
}
