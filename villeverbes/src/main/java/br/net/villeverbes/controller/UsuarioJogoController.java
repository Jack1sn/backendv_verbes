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
@RequestMapping("/api/jogo")
public class UsuarioJogoController {

    private final UsuarioJogoService usuarioJogoService;

    // Injeção de dependência do serviço através do construtor
    public UsuarioJogoController(UsuarioJogoService usuarioJogoService) {
        this.usuarioJogoService = usuarioJogoService;
    }

    /**
     * Endpoint para criar um novo jogo.
     * Recebe um DTO com as informações do jogo.
     */
    @PostMapping("/{usuarioId}")
    public ResponseEntity<UsuarioJogoEntity> criarJogo(@PathVariable Long usuarioId, @RequestBody UsuarioJogoDTO jogoDTO) {
        try {
            UsuarioJogoEntity jogoCriado = usuarioJogoService.criarJogo(usuarioId, jogoDTO);
            return new ResponseEntity<>(jogoCriado, HttpStatus.CREATED); // Retorna 201 Created
        } catch (Exception e) {
            return new ResponseEntity<> ( HttpStatus.BAD_REQUEST); // Caso haja algum erro
        }
    }

    /**
     * Endpoint para listar todos os jogos de um usuário.
     */
    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<UsuarioJogoEntity>> listarJogos(@PathVariable Long usuarioId) {
        List<UsuarioJogoEntity> jogos = usuarioJogoService.listarJogosPorUsuario(usuarioId);
        if (jogos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Caso não encontre jogos
        }
        return new ResponseEntity<>(jogos, HttpStatus.OK);
    }

    /**
     * Endpoint para obter um jogo específico de um usuário.
     */
    @GetMapping("/{usuarioId}/{jogoId}")
    public ResponseEntity<UsuarioJogoEntity> obterJogo(@PathVariable Long usuarioId, @PathVariable Long jogoId) {
        UsuarioJogoEntity jogo = usuarioJogoService.obterJogoPorId(usuarioId, jogoId);
        if (jogo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Caso não encontre o jogo
        }
        return new ResponseEntity<>(jogo, HttpStatus.OK);
    }
}
