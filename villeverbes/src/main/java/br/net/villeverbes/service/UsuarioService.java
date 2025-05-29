package br.net.villeverbes.service;

import br.net.villeverbes.dto.UsuarioDTO;
import br.net.villeverbes.entity.UsuarioEntity;
import br.net.villeverbes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Método privado genérico para salvar usuário com perfil e senha
    @Transactional
    private UsuarioEntity salvarUsuarioComPerfil(UsuarioDTO dto, String senha, String perfil) {
        if (dto.getNome() == null || dto.getEmail() == null) {
            throw new IllegalArgumentException("Nome e e-mail são obrigatórios");
        }

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        String senhaCriptografada = passwordEncoder.encode(senha);

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(senhaCriptografada);
        usuario.setPerfil(perfil);
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setCpf(dto.getCpf());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEndereco(dto.getEndereco());
        usuario.setCep(dto.getCep());
        usuario.setNumero(dto.getNumero());
        usuario.setComplemento(dto.getComplemento());
        usuario.setBairro(dto.getBairro());
        usuario.setCidade(dto.getCidade());
        usuario.setEstado(dto.getEstado());
        usuario.setLogin(dto.getEmail());

        return usuarioRepository.save(usuario);
    }

    // Salvar jogador com senha gerada automaticamente
    @Transactional
    public UsuarioEntity salvarJogador(UsuarioDTO dto) {
        String senhaTemporaria = gerarSenhaTemporaria();
        return salvarUsuarioComPerfil(dto, senhaTemporaria, "JOGADOR");
    }

    // Salvar colaborador com senha fornecida
    @Transactional
    public UsuarioEntity salvarColaborador(UsuarioDTO dto) {
        if (dto.getSenha() == null || dto.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha para colaborador é obrigatória");
        }
        return salvarUsuarioComPerfil(dto, dto.getSenha(), "COLABORADOR");
    }

    // Buscar por e-mail
    public Optional<UsuarioEntity> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Buscar por ID
    public Optional<UsuarioEntity> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    // Atualizar usuário
    @Transactional
    public UsuarioEntity atualizar(Long id, UsuarioDTO dto) {
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(id);
        if (!usuarioOptional.isPresent()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        UsuarioEntity usuario = usuarioOptional.get();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setPerfil(dto.getPerfil());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setCpf(dto.getCpf());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEndereco(dto.getEndereco());
        usuario.setCep(dto.getCep());
        usuario.setNumero(dto.getNumero());
        usuario.setComplemento(dto.getComplemento());
        usuario.setBairro(dto.getBairro());
        usuario.setCidade(dto.getCidade());
        usuario.setEstado(dto.getEstado());

        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        return usuarioRepository.save(usuario);
    }

    // Excluir usuário
    @Transactional
    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    // Geração simples de senha temporária
    private String gerarSenhaTemporaria() {
        return "senha123"; // Pode trocar por SecureRandom se quiser
    }



    //public List<UsuarioEntity> listarColaboradores() {
    //    return usuarioRepository.findByPerfil("COLABORADOR");
   // }
    
    public List<UsuarioDTO> listarColaboradores() {
    return usuarioRepository.findByPerfil("COLABORADOR")
            .stream()
            .map(UsuarioDTO::new)
            .collect(Collectors.toList());
}

    
}


