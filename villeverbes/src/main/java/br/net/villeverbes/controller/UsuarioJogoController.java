package br.net.villeverbes.controller;

import br.net.villeverbes.dto.UsuarioJogoDTO;
import br.net.villeverbes.entity.UsuarioJogoEntity;
import br.net.villeverbes.service.UsuarioJogoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") // CORS para o frontend Angular
@RestController
@RequestMapping("/api/jogos") // Padrão REST plural
public class UsuarioJogoController {

    private final UsuarioJogoService usuarioJogoService;

    public UsuarioJogoController(UsuarioJogoService usuarioJogoService) {
        this.usuarioJogoService = usuarioJogoService;
    }

    /**
     * Criar novo jogo para um usuário
     */
    @PostMapping("/{usuarioId}")
    public ResponseEntity<UsuarioJogoEntity> criarJogo(
            @PathVariable Long usuarioId,
            @RequestBody UsuarioJogoDTO jogoDTO) {
        try {
            UsuarioJogoEntity novoJogo = usuarioJogoService.criarJogo(usuarioId, jogoDTO);
            return new ResponseEntity<>(novoJogo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Listar todos os jogos de um usuário
     */
    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<UsuarioJogoEntity>> listarJogosPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<UsuarioJogoEntity> jogos = usuarioJogoService.listarJogosPorUsuario(usuarioId);
            if (jogos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(jogos);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obter um jogo específico de um usuário
     */
    @GetMapping("/{usuarioId}/{jogoId}")
    public ResponseEntity<UsuarioJogoEntity> obterJogo(
            @PathVariable Long usuarioId,
            @PathVariable Long jogoId) {
        try {
            UsuarioJogoEntity jogo = usuarioJogoService.obterJogoPorId(usuarioId, jogoId);
            return ResponseEntity.ok(jogo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Atualizar um jogo de um usuário
     */
    @PutMapping("/{usuarioId}/{jogoId}")
    public ResponseEntity<UsuarioJogoEntity> atualizarJogo(
            @PathVariable Long usuarioId,
            @PathVariable Long jogoId,
            @RequestBody UsuarioJogoDTO jogoDTO) {
        try {
            UsuarioJogoEntity jogoAtualizado = usuarioJogoService.atualizarJogo(usuarioId, jogoId, jogoDTO);
            return ResponseEntity.ok(jogoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Excluir um jogo de um usuário
     */
    @DeleteMapping("/{usuarioId}/{jogoId}")
    public ResponseEntity<Void> excluirJogo(
            @PathVariable Long usuarioId,
            @PathVariable Long jogoId) {
        try {
            usuarioJogoService.deletarJogo(usuarioId, jogoId);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
