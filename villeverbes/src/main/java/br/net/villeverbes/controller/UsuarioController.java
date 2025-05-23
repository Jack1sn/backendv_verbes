package br.net.villeverbes.controller;

import br.net.villeverbes.dto.UsuarioDTO;
import br.net.villeverbes.entity.UsuarioEntity;
import br.net.villeverbes.service.EmailService;
import br.net.villeverbes.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Optional;

@RestController
@RequestMapping("/jogador")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/autocadastro")
    public ResponseEntity<String> autoCadastro(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            // Verifica se já existe usuário com o e-mail
            Optional<UsuarioEntity> existente = usuarioService.buscarPorEmail(usuarioDTO.getEmail());
            if (existente.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já cadastrado");
            }

            // Gerar senha aleatória de 4 dígitos
            String senhaGerada = gerarSenhaAleatoria();

            // Criar entidade e preencher campos
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setNome(usuarioDTO.getNome());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setSenha(senhaGerada); // será criptografada dentro do service
            usuario.setPerfil("JOGADOR");
            usuario.setDataNascimento(usuarioDTO.getDataNascimento());
            usuario.setCpf(usuarioDTO.getCpf());
            usuario.setTelefone(usuarioDTO.getTelefone());
            usuario.setEndereco(usuarioDTO.getEndereco());
            usuario.setCep(usuarioDTO.getCep());
            usuario.setNumero(usuarioDTO.getNumero());
            usuario.setComplemento(usuarioDTO.getComplemento());
            usuario.setBairro(usuarioDTO.getBairro());
            usuario.setCidade(usuarioDTO.getCidade());
            usuario.setEstado(usuarioDTO.getEstado());
            usuario.setLogin(usuarioDTO.getEmail());

            usuarioService.salvar(usuarioDTO,senhaGerada );

            // Enviar senha por e-mail
            emailService.sendEmail(usuario.getEmail(), "Cadastro realizado com sucesso", 
                "Olá " + usuario.getNome() + ", sua senha é: " + senhaGerada);

            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    private String gerarSenhaAleatoria() {
        SecureRandom random = new SecureRandom();
        int numero = random.nextInt(9000) + 1000; // de 1000 a 9999
        return String.valueOf(numero);
    }
}
