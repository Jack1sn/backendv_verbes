package br.net.villeverbes.config;

import br.net.villeverbes.entity.*;
import br.net.villeverbes.model.EmailAjudaModel;
import br.net.villeverbes.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DBInitializer {

    @Bean
    CommandLineRunner initDatabase(
        UsuarioRepository usuarioRepository,
        UsuarioJogoRepository usuarioJogoRepository,
        EmailAjudaRepository emailAjudaRepository,
        PronomeRepository pronomeRepository,
        VerboInfinitivoRepository verboRepository,
        ComplementoRepository complementoRepository,
        TempoVerbalRepository tempoVerbalRepository,
        FraseCasaRepository fraseCasaRepository
    ) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return args -> {
            // Usuários iniciais
            String senhaCriptografada = passwordEncoder.encode("0505");

            criarUsuarioSeNaoExistir(usuarioRepository, "ana@gmail.com", "Ana Paula", "JOGADOR", senhaCriptografada,
                    LocalDate.parse("1990-01-01"), "ana_paula", "123.456.789-00", "41 98765-4321",
                    "80010-000", "Rua das Flores", "123", "Apto 12", "Centro", "Curitiba", "PR");

            criarAdministradorSeNaoExistir(usuarioRepository, "admin@villedeverbes.com", "Administrador", "ADMIN",
                    senhaCriptografada, LocalDate.parse("1980-05-22"), "admin", "987.654.321-00",
                    "41 91234-5678", "80010-001", "Av. Brasil", "456", "Sala 1", "Centro", "Curitiba", "PR");

            criarColaboradorSeNaoExistir(usuarioRepository, "colaborador1@villedeverbes.com",
                    "Maria Colaboradora", "COLABORADOR", senhaCriptografada,
                    LocalDate.parse("1992-08-15"), "41 99876-5432");

            // Ajuda
            if (emailAjudaRepository.count() == 0) {
                EmailAjudaModel ajuda1 = new EmailAjudaModel("joao@exemplo.com", "Dificuldade no jogo");
                ajuda1.setDataEnvio(LocalDateTime.now().minusDays(1));

                EmailAjudaModel ajuda2 = new EmailAjudaModel("maria@exemplo.com", "Como recuperar senha?");
                ajuda2.setDataEnvio(LocalDateTime.now());

                emailAjudaRepository.save(ajuda1.toEntity());
                emailAjudaRepository.save(ajuda2.toEntity());
            }

            // Pronomes
            if (pronomeRepository.count() == 0) {
                pronomeRepository.saveAll(List.of(
                        new PronomeEntity("Je"),
                        new PronomeEntity("J'"),
                        new PronomeEntity("Tu"),
                        new PronomeEntity("Il"),
                        new PronomeEntity("Elle"),
                        new PronomeEntity("Nous"),
                        new PronomeEntity("Vous"),
                        new PronomeEntity("Ils"),
                        new PronomeEntity("Elles")
                ));
            }

            // Verbos
            if (verboRepository.count() == 0) {
                verboRepository.saveAll(List.of(
                        new VerboInfinitivoEntity("aider"),
                        new VerboInfinitivoEntity("aller"),
                        new VerboInfinitivoEntity("amener"),
                        new VerboInfinitivoEntity("apporter"),
                        new VerboInfinitivoEntity("asseoir"),
                        new VerboInfinitivoEntity("avoir"),
                        new VerboInfinitivoEntity("boire"),
                        new VerboInfinitivoEntity("brosser"),
                        new VerboInfinitivoEntity("coucher"),
                        new VerboInfinitivoEntity("chanter"),
                        new VerboInfinitivoEntity("comprendre"),
                        new VerboInfinitivoEntity("déjeuner"),
                        new VerboInfinitivoEntity("donner"),
                        new VerboInfinitivoEntity("écouter"),
                        new VerboInfinitivoEntity("écrire"),
                        new VerboInfinitivoEntity("excuser"),
                        new VerboInfinitivoEntity("faire"),
                        new VerboInfinitivoEntity("laver"),
                        new VerboInfinitivoEntity("lire"),
                        new VerboInfinitivoEntity("manger"),
                        new VerboInfinitivoEntity("ouvrir"),
                        new VerboInfinitivoEntity("parler"),
                        new VerboInfinitivoEntity("prendre"),
                        new VerboInfinitivoEntity("promener"),
                        new VerboInfinitivoEntity("ranger"),
                        new VerboInfinitivoEntity("répéter"),
                        new VerboInfinitivoEntity("regarder"),
                        new VerboInfinitivoEntity("rester"),
                        new VerboInfinitivoEntity("se brosser"),
                        new VerboInfinitivoEntity("se coucher"),
                        new VerboInfinitivoEntity("se lever"),
                        new VerboInfinitivoEntity("travailler"),
                        new VerboInfinitivoEntity("oublier"),
                        new VerboInfinitivoEntity("jouer"),
                        new VerboInfinitivoEntity("préparer")
                ));
            }

            // Complementos
            if (complementoRepository.count() == 0) {
                complementoRepository.saveAll(List.of(
                        new ComplementoEntity("une pomme"),
                        new ComplementoEntity("à l'école"),
                        new ComplementoEntity("un livre"),
                        new ComplementoEntity("du sport"),
                        new ComplementoEntity("la télévision"),
                        new ComplementoEntity("au football"),
                        new ComplementoEntity("de l'eau"),
                        new ComplementoEntity("une lettre"),
                        new ComplementoEntity("le bus"),
                        new ComplementoEntity("le chocolat"),
                        new ComplementoEntity("mes yeux"),
                        new ComplementoEntity("français"),
                        new ComplementoEntity("sept heures"),
                        new ComplementoEntity("mon petit-déjeuner"),
                        new ComplementoEntity("les dents"),
                        new ComplementoEntity("mon lit"),
                        new ComplementoEntity("mes vêtements"),
                        new ComplementoEntity("à midi"),
                        new ComplementoEntity("mes devoirs"),
                        new ComplementoEntity("les devoirs"),
                        new ComplementoEntity("en famille"),
                        new ComplementoEntity("dix heures"),
                        new ComplementoEntity("la musique"),
                        new ComplementoEntity("le dîner"),
                       new ComplementoEntity("à la maison"),
                        new ComplementoEntity("les informations"),
                        new ComplementoEntity("du ménage"),
                        new ComplementoEntity("le film"), 
                        new ComplementoEntity("la radio"),
                             new ComplementoEntity("la chambre")      // Adicionando "la musique"
                ));
            }

            // Tempos verbais
            if (tempoVerbalRepository.count() == 0) {
                tempoVerbalRepository.saveAll(List.of(
                        new TempoVerbalEntity("Présent"),
                        new TempoVerbalEntity("Passé Composé"),
                        new TempoVerbalEntity("Imparfait"),
                        new TempoVerbalEntity("Futur Simple"),
                        new TempoVerbalEntity("Futur Proche"),
                        new TempoVerbalEntity("Passé Simple"),
                        new TempoVerbalEntity("Conditionnnel")
                ));
            }

            // Frases no ambiente doméstico (FraseCasa)
            if (fraseCasaRepository.count() == 0) {
                TempoVerbalEntity presente = tempoVerbalRepository.findByTempo("Présent").orElseThrow();

                List<FraseAmbienteCasaEntity> frases = List.of(
                    new FraseAmbienteCasaEntity(
                        buscarPronome("je", pronomeRepository),
                        buscarVerbo("manger", verboRepository),
                        buscarComplemento("une pomme", complementoRepository),
                        presente,
                        "mange"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("tu", pronomeRepository),
                        buscarVerbo("aller", verboRepository),
                        buscarComplemento("à l'école", complementoRepository),
                        presente,
                        "vas"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("il", pronomeRepository),
                        buscarVerbo("lire", verboRepository),
                        buscarComplemento("un livre", complementoRepository),
                        presente,
                        "lit"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("elle", pronomeRepository),
                        buscarVerbo("faire", verboRepository),
                        buscarComplemento("du sport", complementoRepository),
                        presente,
                        "fait"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("nous", pronomeRepository),
                        buscarVerbo("regarder", verboRepository),
                        buscarComplemento("la télévision", complementoRepository),
                        presente,
                        "regardons"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("vous", pronomeRepository),
                        buscarVerbo("jouer", verboRepository),
                        buscarComplemento("au football", complementoRepository),
                        presente,
                        "jouez"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("je", pronomeRepository),
                        buscarVerbo("laver", verboRepository),
                        buscarComplemento("mes vêtements", complementoRepository),
                        presente,
                        "lave"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("tu", pronomeRepository),
                        buscarVerbo("répéter", verboRepository),
                        buscarComplemento("mes devoirs", complementoRepository),
                        presente,
                        "répètes"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("il", pronomeRepository),
                        buscarVerbo("écouter", verboRepository),
                        buscarComplemento("la musique", complementoRepository),
                        presente,
                        "écoute"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("ils", pronomeRepository),
                        buscarVerbo("regarder", verboRepository),
                        buscarComplemento("le bus", complementoRepository),
                        presente,
                        "regardent"
                    ),
                    // Frase adicional para completar 23
                    new FraseAmbienteCasaEntity(
                        buscarPronome("elles", pronomeRepository),
                        buscarVerbo("parler", verboRepository),
                        buscarComplemento("en famille", complementoRepository),
                        presente,
                        "parlent"
                    ),
                    new FraseAmbienteCasaEntity(
    buscarPronome("je", pronomeRepository),
    buscarVerbo("faire", verboRepository),
    buscarComplemento("les devoirs", complementoRepository),
    presente,
    "fais"
),
new FraseAmbienteCasaEntity(
    buscarPronome("tu", pronomeRepository),
    buscarVerbo("lire", verboRepository),
    buscarComplemento("un livre", complementoRepository),
    presente,
    "lis"
),
new FraseAmbienteCasaEntity(
    buscarPronome("il", pronomeRepository),
    buscarVerbo("écouter", verboRepository),
    buscarComplemento("la musique", complementoRepository),
    presente,
    "écoute"
),
new FraseAmbienteCasaEntity(
    buscarPronome("elle", pronomeRepository),
    buscarVerbo("travailler", verboRepository),
    buscarComplemento("à la maison", complementoRepository),
    presente,
    "travaille"
),
new FraseAmbienteCasaEntity(
    buscarPronome("nous", pronomeRepository),
    buscarVerbo("manger", verboRepository),
    buscarComplemento("à midi", complementoRepository),
    presente,
    "mangeons"
),
new FraseAmbienteCasaEntity(
    buscarPronome("vous", pronomeRepository),
    buscarVerbo("boire", verboRepository),
    buscarComplemento("de l'eau", complementoRepository),
    presente,
    "buvez"
),
new FraseAmbienteCasaEntity(
    buscarPronome("ils", pronomeRepository),
    buscarVerbo("faire", verboRepository),
    buscarComplemento("du ménage", complementoRepository),
    presente,
    "font"
),
new FraseAmbienteCasaEntity(
    buscarPronome("elles", pronomeRepository),
    buscarVerbo("regarder", verboRepository),
    buscarComplemento("le film", complementoRepository),
    presente,
    "regardent"
),
new FraseAmbienteCasaEntity(
    buscarPronome("je", pronomeRepository),
    buscarVerbo("répéter", verboRepository),
    buscarComplemento("les informations", complementoRepository),
    presente,
    "répète"
),
new FraseAmbienteCasaEntity(
    buscarPronome("tu", pronomeRepository),
    buscarVerbo("écouter", verboRepository),
    buscarComplemento("la radio", complementoRepository),
    presente,
    "écoutes"
),
new FraseAmbienteCasaEntity(
    buscarPronome("nous", pronomeRepository),
    buscarVerbo("ranger", verboRepository),
    buscarComplemento("la chambre", complementoRepository),
    presente,
    "rangeons"
),
new FraseAmbienteCasaEntity(
    buscarPronome("vous", pronomeRepository),
    buscarVerbo("préparer", verboRepository),
    buscarComplemento("le dîner", complementoRepository),
    presente,
    "préparez"
),
new FraseAmbienteCasaEntity(
    buscarPronome("vous", pronomeRepository),
    buscarVerbo("écouter", verboRepository),
    buscarComplemento("la musique", complementoRepository),
    presente,
    "écoutez"
),
new FraseAmbienteCasaEntity(
    buscarPronome("vous", pronomeRepository),
    buscarVerbo("écouter", verboRepository),
    buscarComplemento("la musique", complementoRepository),
    presente,
    "écoutez"
),new FraseAmbienteCasaEntity(
    buscarPronome("vous", pronomeRepository),
    buscarVerbo("boire", verboRepository),
    buscarComplemento("de l'eau", complementoRepository),
    presente,
    "buvez"
),
new FraseAmbienteCasaEntity(
    buscarPronome("ils", pronomeRepository),
    buscarVerbo("faire", verboRepository),
    buscarComplemento("du ménage", complementoRepository),
    presente,
    "font"
),
new FraseAmbienteCasaEntity(
    buscarPronome("elles", pronomeRepository),
    buscarVerbo("regarder", verboRepository),
    buscarComplemento("le film", complementoRepository),
    presente,
    "regardent"
),
new FraseAmbienteCasaEntity(
    buscarPronome("je", pronomeRepository),
    buscarVerbo("répéter", verboRepository),
    buscarComplemento("les informations", complementoRepository),
    presente,
    "répète"
),
new FraseAmbienteCasaEntity(
    buscarPronome("tu", pronomeRepository),
    buscarVerbo("écouter", verboRepository),
    buscarComplemento("la radio", complementoRepository),
    presente,
    "écoutes"
),
new FraseAmbienteCasaEntity(
    buscarPronome("nous", pronomeRepository),
    buscarVerbo("ranger", verboRepository),
    buscarComplemento("la chambre", complementoRepository),
    presente,
    "rangeons"
),
new FraseAmbienteCasaEntity(
    buscarPronome("vous", pronomeRepository),
    buscarVerbo("préparer", verboRepository),
    buscarComplemento("le dîner", complementoRepository),
    presente,
    "préparez"
),
new FraseAmbienteCasaEntity(
    buscarPronome("vous", pronomeRepository),
    buscarVerbo("écouter", verboRepository),
    buscarComplemento("la musique", complementoRepository),
    presente,
    "écoutez"
),
new FraseAmbienteCasaEntity(
    buscarPronome("vous", pronomeRepository),
    buscarVerbo("écouter", verboRepository),
    buscarComplemento("la musique", complementoRepository),
    presente,
    "écoutez"
)





                );

                fraseCasaRepository.saveAll(frases);
                System.out.println("✅ Frases cadastradas em tb_ambiente_casa.");
            }
        };
    }

    private PronomeEntity buscarPronome(String texto, PronomeRepository repo) {
        return repo.findByTexto(texto).orElseThrow(() -> new RuntimeException("Pronome não encontrado: " + texto));
    }

    private VerboInfinitivoEntity buscarVerbo(String verbo, VerboInfinitivoRepository repo) {
        return repo.findByVerbo(verbo).orElseThrow(() -> new RuntimeException("Verbo não encontrado: " + verbo));
    }

    private ComplementoEntity buscarComplemento(String descricao, ComplementoRepository repo) {
        return repo.findByDescricao(descricao).orElseThrow(() -> new RuntimeException("Complemento não encontrado: " + descricao));
    }

    private void criarColaboradorSeNaoExistir(UsuarioRepository repo, String email, String nome, String perfil,
                                              String senha, LocalDate nascimento, String telefone) {
        if (!repo.existsByEmail(email)) {
            UsuarioEntity u = new UsuarioEntity();
            u.setEmail(email);
            u.setNome(nome);
            u.setPerfil(perfil);
            u.setSenha(senha);
            u.setDataNascimento(nascimento);
            u.setTelefone(telefone);
            repo.save(u);
        }
    }

    private void criarUsuarioSeNaoExistir(UsuarioRepository repo, String email, String nome, String perfil,
                                          String senha, LocalDate nascimento, String login, String cpf,
                                          String telefone, String cep, String endereco, String numero,
                                          String complemento, String bairro, String cidade, String estado) {
        if (!repo.existsByEmail(email)) {
            UsuarioEntity u = new UsuarioEntity();
            u.setEmail(email);
            u.setNome(nome);
            u.setPerfil(perfil);
            u.setSenha(senha);
            u.setDataNascimento(nascimento);
            u.setLogin(login);
            u.setCpf(cpf);
            u.setTelefone(telefone);
            u.setCep(cep);
            u.setEndereco(endereco);
            u.setNumero(numero);
            u.setComplemento(complemento);
            u.setBairro(bairro);
            u.setCidade(cidade);
            u.setEstado(estado);
            repo.save(u);
        }
    }

    private void criarAdministradorSeNaoExistir(UsuarioRepository repo, String email, String nome, String perfil,
                                                String senha, LocalDate nascimento, String login, String cpf,
                                                String telefone, String cep, String endereco, String numero,
                                                String complemento, String bairro, String cidade, String estado) {
        criarUsuarioSeNaoExistir(repo, email, nome, perfil, senha, nascimento, login, cpf, telefone, cep, endereco, numero, complemento, bairro, cidade, estado);
    }
}
