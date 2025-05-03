package br.net.villeverbes.config;

import br.net.villeverbes.model.Usuario;
import br.net.villeverbes.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DBInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Cria o encoder de senha
        
        return args -> {
            // Criptografando a senha fixa "0505" para todos os usuários
            String senhaCriptografada = passwordEncoder.encode("0505");
            
            // Criando clientes
            usuarioRepository.save(new Usuario(0, "Ana Paula", "ana@gmail.com", senhaCriptografada, "CLIENTE", "123.456.789-00", "41 98765-4321", "80010-000", "Rua das Flores", "123", "Apto 12", "Centro", "Curitiba", "PR", null, null));
            usuarioRepository.save(new Usuario(0, "Roberto Lima", "roberto.lima@gmail.com", senhaCriptografada, "CLIENTE", "987.654.321-00", "41 99876-5432", "80020-000", "Av. dos Pinhais", "456", "Casa", "Batel", "Pinhais", "PR", null, null));
            
            // Criando funcionários
            Usuario funcionario = new Usuario();
            funcionario.setNome("Neres Silva");
            funcionario.setEmail("silva@empresa.com");
            funcionario.setPerfil("FUNCIONARIO");
            funcionario.setSenha(senhaCriptografada);  // A senha criptografada
            funcionario.setDataNascimento("1990-01-01");

            Usuario funcionario1 = new Usuario();
            funcionario1.setNome("Jack");
            funcionario1.setEmail("jackson@villedesverbes.com");
            funcionario1.setPerfil("FUNCIONARIO");
            funcionario1.setSenha(senhaCriptografada);  // A senha criptografada
            funcionario1.setDataNascimento("1990-01-01");

            // Criando administrador
            Usuario admin = new Usuario();
            admin.setEmail("admin@villedesverbes.com");
            admin.setSenha(senhaCriptografada);  // A senha criptografada
            admin.setPerfil("ADMIN");

            // Salvando os dados no banco
            usuarioRepository.save(funcionario);
            usuarioRepository.save(funcionario1);
            usuarioRepository.save(admin);
        };
    }
}
