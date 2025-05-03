package br.net.villeverbes.service;

import br.net.villeverbes.model.Usuario;
import br.net.villeverbes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        UserBuilder builder = User.withUsername(email)
                .password(usuario.getSenha())
                .roles(usuario.getPerfil()); // ADMIN, FUNCIONARIO, CLIENTE...

        return builder.build();
    }
}
