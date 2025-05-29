package br.net.villeverbes.config;

import br.net.villeverbes.entity.EmailAjudaEntity;
import br.net.villeverbes.entity.UsuarioEntity;
import br.net.villeverbes.repository.UsuarioRepository;
import br.net.villeverbes.model.EmailAjudaModel;
import br.net.villeverbes.repository.EmailAjudaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class DBInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, EmailAjudaRepository emailAjudaRepository) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return args -> {
            // Senha padrão criptografada
            String senhaCriptografada = passwordEncoder.encode("0505");

            // Criar usuário Jogador e Administrador com dados específicos
            criarUsuarioSeNaoExistir(usuarioRepository, "ana@gmail.com", "Ana Paula", "JOGADOR", senhaCriptografada, 
                                      LocalDate.parse("1990-01-01"), "ana_paula", "123.456.789-00", "41 98765-4321", 
                                      "80010-000", "Rua das Flores", "123", "Apto 12", "Centro", "Curitiba", "PR");

            criarAdministradorSeNaoExistir(usuarioRepository, "admin@villedeverbes.com", "Administrador", "ADMIN", 
                                           senhaCriptografada, LocalDate.parse("1980-05-22"), "admin", 
                                           "987.654.321-00", "41 91234-5678", "80010-001", "Avenida Brasil", 
                                           "456", "Sala 1", "Centro", "Curitiba", "PR");

            criarColaboradorSeNaoExistir(
                usuarioRepository,
                "colaborador1@villedeverbes.com",
                "Maria Colaboradora",
                "COLABORADOR",
                senhaCriptografada,
                LocalDate.parse("1992-08-15"),
                "41 99876-5432"
            );

            // Inserir mensagens de ajuda no banco se ainda não existirem
            if (emailAjudaRepository.count() == 0) {
                EmailAjudaModel ajuda1Model = new EmailAjudaModel("joao@exemplo.com", "Estou com dificuldades para acessar o jogo.");
                ajuda1Model.setDataEnvio(LocalDateTime.now().minusDays(1)); // Só se setter existir!
            
                EmailAjudaModel ajuda2Model = new EmailAjudaModel("maria@exemplo.com", "Como posso recuperar minha senha?");
                ajuda2Model.setDataEnvio(LocalDateTime.now());
            
                EmailAjudaEntity ajuda1Entity = ajuda1Model.toEntity();
                EmailAjudaEntity ajuda2Entity = ajuda2Model.toEntity();
            
                emailAjudaRepository.save(ajuda1Entity);
                emailAjudaRepository.save(ajuda2Entity);
            
            

                System.out.println("Emails de ajuda iniciais adicionados com sucesso.");
            } else {
                System.out.println("Emails de ajuda já existentes no banco.");
            }
        };
    }

    private void criarColaboradorSeNaoExistir(
        UsuarioRepository usuarioRepository,
        String email,
        String nome,
        String perfil,
        String senhaCriptografada,
        LocalDate dataNascimento,
        String telefone
    ) {
        if (!usuarioRepository.existsByEmail(email)) {
            UsuarioEntity colaborador = new UsuarioEntity();
            colaborador.setEmail(email);
            colaborador.setNome(nome);
            colaborador.setPerfil(perfil);
            colaborador.setSenha(senhaCriptografada);
            colaborador.setDataNascimento(dataNascimento);
            colaborador.setTelefone(telefone);

            usuarioRepository.save(colaborador);
            System.out.println("Colaborador '" + nome + "' criado com sucesso.");
        } else {
            System.out.println("Colaborador '" + nome + "' já existe no banco de dados.");
        }
    }

    private void criarUsuarioSeNaoExistir(UsuarioRepository usuarioRepository, String email, String nome, String perfil, 
                                           String senhaCriptografada, LocalDate dataNascimento, String login, String cpf, 
                                           String telefone, String cep, String endereco, String numero, String complemento, 
                                           String bairro, String cidade, String estado) {
        if (!usuarioRepository.existsByEmail(email)) {
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senhaCriptografada);
            usuario.setPerfil(perfil);
            usuario.setDataNascimento(dataNascimento);
            usuario.setLogin(login);
            usuario.setCpf(cpf);
            usuario.setTelefone(telefone);
            usuario.setCep(cep);
            usuario.setEndereco(endereco);
            usuario.setNumero(numero);
            usuario.setComplemento(complemento);
            usuario.setBairro(bairro);
            usuario.setCidade(cidade);
            usuario.setEstado(estado);

            usuarioRepository.save(usuario);
            System.out.println("Usuário '" + nome + "' criado com sucesso.");
        } else {
            System.out.println("Usuário '" + nome + "' já existe no banco de dados.");
        }
    }

    private void criarAdministradorSeNaoExistir(UsuarioRepository usuarioRepository, String email, String nome, String perfil, 
                                                 String senhaCriptografada, LocalDate dataNascimento, String login, String cpf, 
                                                 String telefone, String cep, String endereco, String numero, String complemento, 
                                                 String bairro, String cidade, String estado) {
        if (!usuarioRepository.existsByEmail(email)) {
            UsuarioEntity admin = new UsuarioEntity();
            admin.setNome(nome);
            admin.setEmail(email);
            admin.setSenha(senhaCriptografada);
            admin.setPerfil(perfil);
            admin.setDataNascimento(dataNascimento);
            admin.setLogin(login);
            admin.setCpf(cpf);
            admin.setTelefone(telefone);
            admin.setCep(cep);
            admin.setEndereco(endereco);
            admin.setNumero(numero);
            admin.setComplemento(complemento);
            admin.setBairro(bairro);
            admin.setCidade(cidade);
            admin.setEstado(estado);

            usuarioRepository.save(admin);
            System.out.println("Administrador '" + nome + "' criado com sucesso.");
        } else {
            System.out.println("Administrador '" + nome + "' já existe no banco de dados.");
        }
    }
}
