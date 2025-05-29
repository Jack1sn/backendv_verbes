package br.net.villeverbes.service;

import br.net.villeverbes.dto.UsuarioDTO;
import br.net.villeverbes.entity.UsuarioEntity;
import br.net.villeverbes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Método genérico para salvar usuário com perfil e senha (já criptografada)
     */
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

    /**
     * Salva jogador com senha gerada aleatoriamente
     */
  @Transactional
public UsuarioEntity salvarJogador(UsuarioDTO dto) {
    if (dto.getSenha() == null || dto.getSenha().isEmpty()) {
        throw new IllegalArgumentException("Senha para jogador é obrigatória");
    }
    return salvarUsuarioComPerfil(dto, dto.getSenha(), "JOGADOR");
}


    /**
     * Salva colaborador com senha fornecida no DTO
     */
    @Transactional
    public UsuarioEntity salvarColaborador(UsuarioDTO dto) {
        if (dto.getSenha() == null || dto.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha para colaborador é obrigatória");
        }
        return salvarUsuarioComPerfil(dto, dto.getSenha(), "COLABORADOR");
    }

    public Optional<UsuarioEntity> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Optional<UsuarioEntity> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Atualiza dados de um usuário, incluindo a senha se fornecida
     */
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

    @Transactional
    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    /**
     * Gera uma senha temporária de 6 dígitos numéricos aleatórios
     */
    private String gerarSenhaTemporaria() {
        SecureRandom random = new SecureRandom();
        int numero = random.nextInt(900000) + 100000; // entre 100000 e 999999
        return String.valueOf(numero);
    }

    /**
     * Lista colaboradores convertidos para DTO
     */
    public List<UsuarioDTO> listarColaboradores() {
        return usuarioRepository.findByPerfil("COLABORADOR")
                .stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }
}
