package br.net.villeverbes.rest;



import br.net.villeverbes.dto.UsuarioDTO;
import br.net.villeverbes.entity.UsuarioEntity;
import br.net.villeverbes.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuario")
public class UsuarioREST {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Cadastro de Jogador - senha é gerada internamente
     */
    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarJogador(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.salvarJogador(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Jogador cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar jogador: " + e.getMessage());
        }
    }

    /**
     * Cadastro de Colaborador - senha vem no DTO
     */
    @PostMapping("/colaboradores")
    public ResponseEntity<String> cadastrarColaborador(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.salvarColaborador(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Colaborador cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar colaborador: " + e.getMessage());
        }
    }



    /**
     * Endpoint para buscar usuário por ID.
     * 
     * @param id ID do usuário
     * @return Resposta HTTP com o usuário encontrado ou erro 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> findById(@PathVariable Long id) {
        Optional<UsuarioEntity> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Endpoint para buscar usuário por e-mail.
     * 
     * @param email E-mail do usuário
     * @return Resposta HTTP com o usuário encontrado ou erro 404
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioEntity> buscarPorEmail(@PathVariable String email) {
        Optional<UsuarioEntity> usuario = usuarioService.buscarPorEmail(email);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Endpoint para atualizar dados do usuário.
     * 
     * @param id         ID do usuário
     * @param usuarioDTO Dados do usuário para atualização
     * @return Resposta HTTP com status de sucesso ou falha
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.atualizar(id, usuarioDTO);
            return ResponseEntity.ok("Usuário atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    /**
     * Endpoint para excluir usuário.
     * 
     * @param id ID do usuário a ser excluído
     * @return Resposta HTTP com status de sucesso ou falha
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        try {
            usuarioService.excluir(id);
            return ResponseEntity.ok("Usuário excluído com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir usuário: " + e.getMessage());
        }
    }
}
