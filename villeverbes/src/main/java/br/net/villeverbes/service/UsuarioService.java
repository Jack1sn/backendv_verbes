package br.net.villeverbes.service;

import br.net.villeverbes.dto.UsuarioDTO;
import br.net.villeverbes.entity.UsuarioEntity;
import br.net.villeverbes.repository.UsuarioRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Salva os dados do usuário.
     * 
     * @param dto          Dados do usuário
     * @param senhaSimples Senha temporária a ser criptografada
     * @return UsuarioEntity salvo
     */
    @Transactional
    public UsuarioEntity salvar(UsuarioDTO dto, String senhaSimples) {
        try{ 
        System.out.println("Iniciando salvar  o usuário... " );
        // 1. Verificações básicas (pode-se evoluir para validações customizadas)
        if (dto.getNome() == null || dto.getEmail() == null || dto.getPerfil() == null) {
            throw new IllegalArgumentException("Nome, e-mail e perfil são obrigatórios");
        }

        // 2. Verificar se o e-mail já está cadastrado
        Optional<UsuarioEntity> usuarioExistente = usuarioRepository.findByEmail(dto.getEmail());
        if (usuarioExistente.isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        // 3. Criptografar a senha
        String senhaCriptografada = passwordEncoder.encode(senhaSimples);

        // 4. Criar o usuário
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(senhaCriptografada);
        usuario.setPerfil(dto.getPerfil()); // O perfil pode ser "JOGADOR", "GERENTE", etc.
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
        usuario.setLogin(dto.getEmail()); // Pode ser usado para login

        //5. Salvar usuário no banco
        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuario);
        System.out.println("Usuário " + dto.getNome() + " salvo com sucesso!");
        return usuarioSalvo;

    } catch (Exception e) {
        // Log de erro
        System.err.println("Erro ao salvar o usuário: " + e.getMessage());
        e.printStackTrace(); // Opcional: imprime o stack trace completo
        throw new RuntimeException("Erro ao salvar o usuário", e); // Lança uma exceção para ser tratada em outro lugar
    }
    
}

    /**
     * Verifica se o e-mail já está cadastrado no banco.
     * 
     * @param email E-mail a ser verificado
     * @return Optional<UsuarioEntity> Se o e-mail já estiver cadastrado, retorna um usuário
     */
    public Optional<UsuarioEntity> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /**
     * Atualiza os dados de um usuário.
     * 
     * @param id  ID do usuário a ser atualizado
     * @param dto Dados atualizados do usuário
     * @return UsuarioEntity Atualizado
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
            usuario.setSenha(passwordEncoder.encode(dto.getSenha())); // Atualizar senha
        }
   
        return usuarioRepository.save(usuario);
    }

    /**
     * Exclui um usuário pelo ID.
     * 
     * @param id ID do usuário a ser excluído
     */
    @Transactional
    public void excluir(Long id) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
    }

    /**
     * Busca um usuário pelo ID.
     * 
     * @param id ID do usuário a ser buscado
     * @return Optional<UsuarioEntity> Se o usuário for encontrado, retorna o usuário
     */
    public Optional<UsuarioEntity> findById(Long id) {
        return usuarioRepository.findById(id);
    }
}
