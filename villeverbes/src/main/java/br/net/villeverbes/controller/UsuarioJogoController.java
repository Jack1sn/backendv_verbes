package br.net.villeverbes.controller;

import br.net.villeverbes.dto.UsuarioJogoDTO;  // Importando o DTO
import br.net.villeverbes.entity.UsuarioJogoEntity;
import br.net.villeverbes.service.UsuarioJogoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")  // Permitir CORS para o frontend Angular
@RestController
@RequestMapping("/api/usuario-jogo")  // Alterado para refletir mais claramente o propósito do endpoint
public class UsuarioJogoController {

    private final UsuarioJogoService usuarioJogoService;

    // Injeção de dependência do serviço através do construtor
    public UsuarioJogoController(UsuarioJogoService usuarioJogoService) {
        this.usuarioJogoService = usuarioJogoService;
    }

    /**
     * Endpoint para criar um novo registro de jogo de um usuário.
     * Recebe um DTO com as informações do jogo.
     */
    @PostMapping("/{usuarioId}")
    public ResponseEntity<UsuarioJogoEntity> criarRegistroJogo(@PathVariable Long usuarioId, @RequestBody UsuarioJogoDTO usuarioJogoDTO) {
        try {
            // Chamando o serviço para criar o registro de jogo do usuário
            UsuarioJogoEntity usuarioJogoCriado = usuarioJogoService.criarJogo(usuarioId, usuarioJogoDTO);
            return new ResponseEntity<>(usuarioJogoCriado, HttpStatus.CREATED); // Retorna 201 Created
        } catch (Exception e) {
            // Mensagem de erro detalhada no catch para facilitar o debug
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Caso haja algum erro, retorna 400 Bad Request
        }
    }

    /**
     * Endpoint para listar todos os registros de jogos de um usuário.
     */
    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<UsuarioJogoEntity>> listarRegistrosJogos(@PathVariable Long usuarioId) {
        List<UsuarioJogoEntity> registrosJogos = usuarioJogoService.listarJogosPorUsuario(usuarioId);
        if (registrosJogos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content caso não haja jogos
        }
        return new ResponseEntity<>(registrosJogos, HttpStatus.OK); // Retorna 200 OK com os jogos
    }

    /**
     * Endpoint para obter um registro específico de jogo de um usuário.
     */
    @GetMapping("/{usuarioId}/{registroId}")
    public ResponseEntity<UsuarioJogoEntity> obterRegistroJogo(@PathVariable Long usuarioId, @PathVariable Long registroId) {
        UsuarioJogoEntity usuarioJogo = usuarioJogoService.obterJogoPorId(usuarioId, registroId);
        if (usuarioJogo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 Not Found se o jogo não for encontrado
        }
        return new ResponseEntity<>(usuarioJogo, HttpStatus.OK); // Retorna 200 OK com o jogo encontrado
    }
}
