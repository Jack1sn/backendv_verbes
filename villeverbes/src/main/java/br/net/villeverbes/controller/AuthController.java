package br.net.villeverbes.controller;

import br.net.villeverbes.dto.LoginDTO;
import br.net.villeverbes.dto.RedefinirSenhaDTO;
import br.net.villeverbes.dto.UsuarioDTO;
import br.net.villeverbes.entity.UsuarioEntity;
import br.net.villeverbes.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ✅ Login básico
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            UsuarioEntity usuario = usuarioService.buscarPorEmail(loginDTO.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

            if (!passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
                return ResponseEntity.badRequest().body(Map.of("mensagem", "Senha incorreta"));
            }

            // (Opcional) aqui você poderia retornar um token JWT
            return ResponseEntity.ok(new UsuarioDTO(usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("mensagem", e.getMessage()));
        }
    }

    // ✅ Redefinir senha
    @PostMapping("/redefinir-senha")
    public ResponseEntity<?> redefinirSenha(@RequestBody RedefinirSenhaDTO dto) {
        try {
            usuarioService.redefinirSenha(dto.getEmail(), dto.getSenhaAtual(), dto.getNovaSenha());
            return ResponseEntity.ok(Map.of("mensagem", "Senha redefinida com sucesso"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("mensagem", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("mensagem", "Erro interno ao redefinir senha"));
        }
    }
}
