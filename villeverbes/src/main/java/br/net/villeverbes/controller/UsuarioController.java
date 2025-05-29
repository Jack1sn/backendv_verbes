package br.net.villeverbes.controller;

import br.net.villeverbes.dto.UsuarioDTO;
import br.net.villeverbes.entity.UsuarioEntity;
import br.net.villeverbes.service.EmailService;
import br.net.villeverbes.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    /**
     * AutoCadastro de Jogador - senha gerada e enviada por email
     */
    @PostMapping("jogador/autocadastro")
    public ResponseEntity<?> autoCadastro(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Optional<UsuarioEntity> existente = usuarioService.buscarPorEmail(usuarioDTO.getEmail());
            if (existente.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Map.of("error", "E-mail já cadastrado"));
            }

            String senhaGerada = gerarSenhaAleatoria();

            UsuarioEntity usuarioSalvo = usuarioService.salvarJogador(usuarioDTO);

            // Enviar e-mail com senha temporária para o usuário
            emailService.sendEmail(usuarioSalvo.getEmail(), "Cadastro realizado com sucesso",
                    "Olá " + usuarioSalvo.getNome() + ", sua senha é: " + senhaGerada);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of("message", "Jogador cadastrado com sucesso! Senha enviada por email."));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Erro ao cadastrar jogador: " + e.getMessage()));
        }
    }

    /**
     * Endpoint para cadastrar um novo colaborador.
     * Valida se o e-mail já está em uso antes de persistir o colaborador.
     */
    @PostMapping("/colaborador")
    public ResponseEntity<?> cadastrarColaborador(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Optional<UsuarioEntity> existente = usuarioService.buscarPorEmail(usuarioDTO.getEmail());
            if (existente.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Map.of("error", "E-mail já cadastrado"));
            }

            UsuarioEntity usuarioSalvo = usuarioService.salvarColaborador(usuarioDTO);

            UsuarioDTO respostaDTO = new UsuarioDTO(usuarioSalvo);

            URI location = URI.create("/usuarios/" + usuarioSalvo.getId());

            return ResponseEntity
                    .created(location)
                    .body(Map.of(
                        "message", "Colaborador cadastrado com sucesso!",
                        "usuario", respostaDTO
                    ));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Erro ao cadastrar colaborador: " + e.getMessage()));
        }
    }

    /**
     * Gera uma senha aleatória de 4 dígitos (pode melhorar para mais complexidade)
     */
    private String gerarSenhaAleatoria() {
        SecureRandom random = new SecureRandom();
        int numero = random.nextInt(9000) + 1000; // número entre 1000 e 9999
        return String.valueOf(numero);
    }

    /**
     * Endpoint para listar todos os colaboradores.
     */
    @GetMapping("/colaboradores")
    public ResponseEntity<?> listarColaboradores() {
        try {
            List<UsuarioDTO> colaboradores = usuarioService.listarColaboradores();
            return ResponseEntity.ok(colaboradores);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erro ao buscar colaboradores: " + e.getMessage()));
        }
    }

    /**
     * Endpoint para excluir um colaborador pelo ID.
     * Agora sem autenticação, será público.
     */
    @DeleteMapping("/colaboradores/{id}")
    public ResponseEntity<?> excluirColaborador(@PathVariable Long id) {
        try {
            // Verifica se o colaborador existe
            Optional<UsuarioEntity> colaboradorExistente = usuarioService.findById(id);
            if (!colaboradorExistente.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Colaborador não encontrado"));
            }

            // Exclui o colaborador
            usuarioService.excluir(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(Map.of("message", "Colaborador excluído com sucesso"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erro ao excluir colaborador: " + e.getMessage()));
        }
    }

    /**
     * Endpoint para atualizar os dados de um colaborador.
     * Sem autenticação, será público.
     */
    @PutMapping("/colaboradores/{id}")
    public ResponseEntity<?> atualizarColaborador(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            Optional<UsuarioEntity> colaboradorExistente = usuarioService.findById(id);
            if (!colaboradorExistente.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Colaborador não encontrado"));
            }

            UsuarioEntity colaboradorAtualizado = usuarioService.atualizar(id, usuarioDTO);

            UsuarioDTO respostaDTO = new UsuarioDTO(colaboradorAtualizado);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Map.of("message", "Colaborador atualizado com sucesso", "usuario", respostaDTO));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erro ao atualizar colaborador: " + e.getMessage()));
        }
    }
}
