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
                        new VerboInfinitivoEntity("préparer"),
                        new VerboInfinitivoEntity("arroser"),
                         new VerboInfinitivoEntity("tirer"),
                        new VerboInfinitivoEntity("fermer"),
                        new VerboInfinitivoEntity("allumer"),
                        new VerboInfinitivoEntity("nettoyer"),
                         new VerboInfinitivoEntity("être"),
                        new VerboInfinitivoEntity("mettre"),
                        new VerboInfinitivoEntity("peindre"),
                        new VerboInfinitivoEntity("arranger"),
                        new VerboInfinitivoEntity("piloter"),
                        new VerboInfinitivoEntity("acheter"),
                        new VerboInfinitivoEntity("éteindre"),
                        new VerboInfinitivoEntity("déposer"),
                        new VerboInfinitivoEntity("venir"),
                        new VerboInfinitivoEntity("marcher"),
                        new VerboInfinitivoEntity("pédaler"),
                        new VerboInfinitivoEntity("arrêter"),
                        new VerboInfinitivoEntity("s'asseoir")
                          

                ));
            }

            // Complementos
            if (complementoRepository.count() == 0) {
                complementoRepository.saveAll(List.of(
                        new ComplementoEntity("une pomme"),
                        new ComplementoEntity("à l'école"),
                        new ComplementoEntity("un livre"),
                        new ComplementoEntity("du sport à vélo"),
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
                        new ComplementoEntity("la chambre"),
                        new ComplementoEntity("le tableau"),
                         new ComplementoEntity("un tableau"),
                         new ComplementoEntity("le canapé"),
                        new ComplementoEntity("la porte"),
                         new ComplementoEntity("le rideau"),
                        new ComplementoEntity("la table"),
                        new ComplementoEntity("les chaises"),
                        new ComplementoEntity("la cheminée"),
                        new ComplementoEntity("dans le miroir"),
                        new ComplementoEntity("l'abat-jour"),
                          new ComplementoEntity("une chaise"),

                        new ComplementoEntity("la fleur"), 
                        new ComplementoEntity("une fleur sur la table"),
                        new ComplementoEntity("dans la cuisine"),
                        new ComplementoEntity("la cuisinière"),
                        new ComplementoEntity("au Parc à vélo"),
                        new ComplementoEntity("sur l'herbe"),
                        new ComplementoEntity("avec la roulette"),
                        new ComplementoEntity("un vélo"),
                        new ComplementoEntity("sur un vélo"),
                        new ComplementoEntity("du vélo tous les jours"),
                        new ComplementoEntity("un quad"),
                        new ComplementoEntity("la roulette"),
                        new ComplementoEntity("au tableau"),
                        new ComplementoEntity("une plume sur le bureau"),
                        new ComplementoEntity("la fenêtre"),
                        new ComplementoEntity("une lecture"),
                         new ComplementoEntity("par la fenêtre"),
                        new ComplementoEntity("les livres"),
                        new ComplementoEntity("sur le canapé"),
                        new ComplementoEntity("un bouquet de fleurs"),
                         new ComplementoEntity("la lampe"),
                        new ComplementoEntity("la fleur sur la table"),
                        new ComplementoEntity("à l'extérieur")
                     
                       

                       

                
                              
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
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("peindre", verboRepository),
    buscarComplemento("un tableau", complementoRepository), // 1- quadro
    presente,
    "peint"
),

new FraseAmbienteCasaEntity(
    buscarPronome("Elle", pronomeRepository),
    buscarVerbo("acheter", verboRepository),
    buscarComplemento("un bouquet de fleurs", complementoRepository), // 2 - flor
    presente,
    "achète"
),

new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("éteindre", verboRepository),
    buscarComplemento("l'abat-jour", complementoRepository), // 3- abajur
    presente,
    "éteint"
),

new FraseAmbienteCasaEntity(
    buscarPronome("Vous", pronomeRepository),
    buscarVerbo("regarder", verboRepository),
    buscarComplemento("dans le miroir", complementoRepository), // 4- espelho
    presente,
    "regardez"
),

new FraseAmbienteCasaEntity(
    buscarPronome("Nous", pronomeRepository),
    buscarVerbo("nettoyer", verboRepository),
    buscarComplemento("la cheminée", complementoRepository), // 5 -chaminé
    presente,
    "nettoyons"
),

new FraseAmbienteCasaEntity(
    buscarPronome("Elle", pronomeRepository),
    buscarVerbo("être", verboRepository),
    buscarComplemento("dans la cuisine", complementoRepository), // 6 - fogão
    presente,
    "est"
),

new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("s'asseoir", verboRepository),
    buscarComplemento("sur le canapé", complementoRepository), // 7 - cadeiras
    presente,
    "s'assied"
),

new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("acheter", verboRepository),
    buscarComplemento("une chaise", complementoRepository), // 8- mesa
    presente,
    "achète"
),

new FraseAmbienteCasaEntity(
    buscarPronome("J'", pronomeRepository),
    buscarVerbo("ouvrir", verboRepository),
    buscarComplemento("le rideau", complementoRepository), // 9 - cortina
    presente,
    "ouvre"
),

new FraseAmbienteCasaEntity(
    buscarPronome("Tu", pronomeRepository),
    buscarVerbo("fermer", verboRepository),
    buscarComplemento("la porte", complementoRepository), // 10- porta
    presente,
    "fermes"
),

new FraseAmbienteCasaEntity(
    buscarPronome("Nous", pronomeRepository),
    buscarVerbo("nettoyer", verboRepository),
    buscarComplemento("le canapé", complementoRepository), // 11- sofá
    presente,
    "nettoyons"
),

//************************** Inicio das frases aleatórias- Ambiente Casa*/
                 
                    new FraseAmbienteCasaEntity(
                        buscarPronome("Elle", pronomeRepository),
                        buscarVerbo("nettoyer", verboRepository),
                        buscarComplemento("le tableau", complementoRepository), // 1 -quadro
                        presente,
                        "nettoie"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("J'", pronomeRepository),   // 2- flor
                        buscarVerbo("acheter", verboRepository),
                        buscarComplemento("un bouquet de fleurs", complementoRepository),
                        presente,
                        "achète"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("Elle", pronomeRepository), //3- abajur
                        buscarVerbo("éteindre", verboRepository),
                        buscarComplemento("la lampe", complementoRepository),
                         presente,
                        "éteint"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("Nous", pronomeRepository),
                        buscarVerbo("regarder", verboRepository), //4-espelho
                        buscarComplemento("dans le miroir", complementoRepository),
                        presente,
                        "regardons"
                    ),
                       new FraseAmbienteCasaEntity(
                        buscarPronome("Elle", pronomeRepository),
                        buscarVerbo("nettoyer", verboRepository), //5-chaminé
                        buscarComplemento("la cheminée", complementoRepository),
                        presente,
                        "nettoie"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("Je", pronomeRepository),
                        buscarVerbo("être", verboRepository), // 6-fogâo
                        buscarComplemento("la cuisinière", complementoRepository),
                        presente,
                        "suis"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("Je", pronomeRepository),  //7- 
                        buscarVerbo("s'asseoir", verboRepository),
                        buscarComplemento("sur le canapé", complementoRepository),
                        presente,
                        "m'assieds"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("J'", pronomeRepository), // 8-mesa
                        buscarVerbo("acheter", verboRepository),
                        buscarComplemento("une chaise", complementoRepository),
                        presente,
                        "achète"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("Il", pronomeRepository),
                        buscarVerbo("ouvrir", verboRepository), //9- cortina
                        buscarComplemento("le rideau", complementoRepository),
                        presente,
                        "ouvre"
                    ),
                    new FraseAmbienteCasaEntity(
                        buscarPronome("J'", pronomeRepository), //10-porta
                        buscarVerbo("ouvrir", verboRepository),
                        buscarComplemento("la porte", complementoRepository),
                        presente,
                        "ouvre"
                    ),
                    
                    new FraseAmbienteCasaEntity(
                        buscarPronome("Elles", pronomeRepository),
                        buscarVerbo("nettoyer", verboRepository),
                        buscarComplemento("le canapé", complementoRepository),//11-sofa
                        presente,
                        "nettoient"
                    ),

        //****
        // */
        //********** */
        //****** */
        // Frases  para ambiente parque

                    new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("écouter", verboRepository),
    buscarComplemento("la musique", complementoRepository),
    presente,
    "écoute"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Elle", pronomeRepository),
    buscarVerbo("venir", verboRepository),
    buscarComplemento("au Parc à vélo", complementoRepository),
    presente,
    "vient"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("marcher", verboRepository),
    buscarComplemento("sur l'herbe", complementoRepository), // -3
    presente,
    "marche"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("jouer", verboRepository),
    buscarComplemento("avec la roulette", complementoRepository),
    presente,
    "joue"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Nous", pronomeRepository),
    buscarVerbo("écouter", verboRepository),
    buscarComplemento("la radio", complementoRepository),
    presente,
    "écoutons"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("faire", verboRepository),
    buscarComplemento("du sport à vélo", complementoRepository), // 6 
    presente,
    "fait"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("pédaler", verboRepository),                    //7
    buscarComplemento("sur un vélo", complementoRepository),
    presente,
    "pédale"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Je", pronomeRepository),
    buscarVerbo("faire", verboRepository),      //8
    buscarComplemento("du vélo tous les jours", complementoRepository),
    presente,
    "fais"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("piloter", verboRepository),    //9
    buscarComplemento("un quad", complementoRepository),
    presente,
    "pilote"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("arrêter", verboRepository),
    buscarComplemento("la roulette", complementoRepository), // 10
    presente,
    "arrête"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),     //11
    buscarVerbo("marcher", verboRepository),
    buscarComplemento("sur l'herbe", complementoRepository),
    presente,
    "marche"
),

///***** frases aleatórias para amb. parque */
new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("écouter", verboRepository),  // 1
    buscarComplemento("la radio", complementoRepository),
    presente,
    "écoute"
),
new FraseAmbienteCasaEntity(            //2
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("venir", verboRepository),
    buscarComplemento("au Parc à vélo", complementoRepository),
    presente,
    "vient"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Nous", pronomeRepository),//3
    buscarVerbo("marcher", verboRepository),
    buscarComplemento("sur l'herbe", complementoRepository),
    presente,
    "marchons"
),new FraseAmbienteCasaEntity(
    buscarPronome("Nous", pronomeRepository),
    buscarVerbo("jouer", verboRepository),
    buscarComplemento("avec la roulette", complementoRepository),
    presente,
    "jouons"
),
new FraseAmbienteCasaEntity(
    buscarPronome("J'", pronomeRepository),
    buscarVerbo("écouter", verboRepository),  //5
    buscarComplemento("la radio", complementoRepository),
    presente,
    "écoute"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Je", pronomeRepository), //6
    buscarVerbo("faire", verboRepository),
    buscarComplemento("du sport à vélo", complementoRepository),
    presente,
    "fais"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Je", pronomeRepository),   //7
    buscarVerbo("pédaler", verboRepository),
    buscarComplemento("un vélo", complementoRepository),
    presente,
    "pédale"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Elle", pronomeRepository),     //8
    buscarVerbo("faire", verboRepository),
    buscarComplemento("du vélo tous les jours", complementoRepository),
    presente,
    "fais"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Je", pronomeRepository),     //9
    buscarVerbo("piloter", verboRepository),
    buscarComplemento("un quad", complementoRepository),
    presente,
    "pilote"
),
new FraseAmbienteCasaEntity(
    buscarPronome("J'", pronomeRepository), //10
    buscarVerbo("arrêter", verboRepository),
    buscarComplemento("la roulette", complementoRepository),
    presente,
    "arrête"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Tu", pronomeRepository), //11
    buscarVerbo("marcher", verboRepository),
    buscarComplemento("sur l'herbe", complementoRepository),
    presente,
    "marches"
),
//////////**
/// /
/// Frases amb. universidade */
new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("écrire", verboRepository),     //1
    buscarComplemento("au tableau", complementoRepository),
    presente,
    "écrit"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Nous", pronomeRepository), //2
    buscarVerbo("écrire", verboRepository),
    buscarComplemento("les informations", complementoRepository),
    presente,
    "écrivons"

),new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("avoir", verboRepository), //3
    buscarComplemento("une plume sur le bureau", complementoRepository),
    presente,
    "a"
), new FraseAmbienteCasaEntity(
    buscarPronome("Elle", pronomeRepository), //4           
    buscarVerbo("ouvrir", verboRepository),
    buscarComplemento("la fenêtre", complementoRepository),
    presente,
    "ouvre"

),new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("écrire", verboRepository), //5     
    buscarComplemento("au tableau", complementoRepository),
    presente,
    "écrit"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Elle", pronomeRepository), //6
    buscarVerbo("ouvrir", verboRepository),
    buscarComplemento("la porte", complementoRepository),
    presente,
    "ouvre"
),new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository), //7
    buscarVerbo("faire", verboRepository),
    buscarComplemento("une lecture", complementoRepository),
    presente,
    "fait"
),
new FraseAmbienteCasaEntity(
    buscarPronome("vous", pronomeRepository), //8
    buscarVerbo("faire", verboRepository),
    buscarComplemento("les devoirs", complementoRepository),
    presente,
    "faites"
),new FraseAmbienteCasaEntity(
    buscarPronome("Tu", pronomeRepository), //9
    buscarVerbo("fermer", verboRepository),
    buscarComplemento("la porte", complementoRepository),
    presente,
    "fermes"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Ils", pronomeRepository), //10
    buscarVerbo("regarder", verboRepository),
    buscarComplemento("par la fenêtre", complementoRepository),
    presente,
    "regardent"
),new FraseAmbienteCasaEntity(  //11
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("lire", verboRepository),
    buscarComplemento("un livre", complementoRepository),
    presente,
    "lit"
),
//*** frases aleatório unversidade */
new FraseAmbienteCasaEntity(
    buscarPronome("Elle", pronomeRepository), //1
    buscarVerbo("écrire", verboRepository),
    buscarComplemento("au tableau", complementoRepository),
    presente,
    "écrit"
),new FraseAmbienteCasaEntity(
    buscarPronome("Vous", pronomeRepository),//2
    buscarVerbo("faire", verboRepository),
    buscarComplemento("les devoirs", complementoRepository),
    presente,
    "faites"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Vous", pronomeRepository),
    buscarVerbo("regarder", verboRepository),//3
    buscarComplemento("le tableau", complementoRepository),
    presente,
    "regardez"
),new FraseAmbienteCasaEntity(  //4
    buscarPronome("Il", pronomeRepository),
    buscarVerbo("ouvrir", verboRepository),
    buscarComplemento("la fenêtre", complementoRepository),
    presente,
    "ouvre"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Nous", pronomeRepository), //5
    buscarVerbo("faire", verboRepository),
    buscarComplemento("les devoirs", complementoRepository),
    presente,
    "faisons"
),new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository), //6
    buscarVerbo("ouvrir", verboRepository),
    buscarComplemento("la porte", complementoRepository),
    presente,
    "ouvre"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Il", pronomeRepository), //7
    buscarVerbo("lire", verboRepository),
    buscarComplemento("un livre", complementoRepository),
    presente,
    "lit"
),new FraseAmbienteCasaEntity(
    buscarPronome("Tu", pronomeRepository), //8
    buscarVerbo("faire", verboRepository),
    buscarComplemento("les devoirs", complementoRepository),
    presente,
    "fais"
),
new FraseAmbienteCasaEntity(
    buscarPronome("J'", pronomeRepository), //9
    buscarVerbo("ouvrir", verboRepository),
    buscarComplemento("la porte", complementoRepository),
    presente,
    "ouvre"
),
new FraseAmbienteCasaEntity(
    buscarPronome("Nous", pronomeRepository),
    buscarVerbo("regarder", verboRepository),//10
    buscarComplemento("par la fenêtre", complementoRepository),
    presente,
    "regardons"
),

new FraseAmbienteCasaEntity(
    buscarPronome("Je", pronomeRepository),
    buscarVerbo("faire", verboRepository), //11
    buscarComplemento("les devoirs", complementoRepository),
    presente,
    "fais"
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
