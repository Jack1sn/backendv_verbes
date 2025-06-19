package br.net.villeverbes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desabilitar CSRF para permitir chamadas de API sem token CSRF
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configuração de CORS
            .authorizeHttpRequests(auth -> auth
                // Permite as requisições OPTIONS (CORS)
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Permite acesso público a endpoints específicos
                .requestMatchers(
                    "/auth/login",                 // Login
                    "/auth/enviar-senha",          // Envio de senha
                    "/jogador/autocadastro",       // Autocadastro de jogador
                    "/email/simples",              // Envio de email simples
                    "/ajuda",                      // Rota de ajuda
                    "/admin/colaboradores",        // Acesso a colaboradores
                    "/usuario/colaboradores",      // Listagem de colaboradores
                    "/usuario/colaborador",        // Cadastro de colaborador
                    "/api/frases-casa",            // Frases públicas
                    "/api/pronomes", "/api/verbos", "/api/tempos", "/api/complementos",  // Recursos públicos
                    "/api/jogo/salvar",            // Salvar jogo (pode ser público ou não, dependendo do caso)

                    // Rotas de jogos públicas (liberando todos os verbos HTTP)
                    "/api/jogo/**"                 // Todos os endpoints relacionados a jogos
                ).permitAll()

                // Permite rotas de colaboradores e jogadores com autenticação
                .requestMatchers(HttpMethod.GET, "/usuario/jogadores").authenticated() // Listar jogadores
                .requestMatchers(HttpMethod.POST, "/usuario/colaborador").authenticated()      // Cadastro colaborador
                .requestMatchers(HttpMethod.PUT, "/usuario/colaboradores").authenticated()    // Atualizar colaborador
                .requestMatchers(HttpMethod.DELETE, "/usuario/colaboradores").authenticated() // Excluir colaborador
                .requestMatchers(HttpMethod.PUT, "/usuario/jogadores").authenticated()       // Atualizar jogador
                .requestMatchers(HttpMethod.PUT, "/usuario/jogadores/{id}/ativo").authenticated() // Bloquear/desbloquear jogador
                .requestMatchers(HttpMethod.GET, "/usuario/colaboradores/**").authenticated() // Ver colaboradores específicos

                // Restringe o restante a usuários autenticados
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) // Filtro de autenticação JWT
            .httpBasic(httpBasic -> {}) // Desabilitar autenticação HTTP Basic
            .formLogin(form -> form.disable()) // Desabilitar o login padrão do Spring
            .logout(logout -> logout.disable()) // Desabilitar logout
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendError(401, "Usuário não autorizado. Por favor, forneça um token válido.");
                })
            );

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(userDetailsService); // Filtro JWT
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Codificador de senha
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200")); // Permitir frontend Angular
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos permitidos
        config.setAllowedHeaders(List.of("Authorization", "Content-Type")); // Cabeçalhos permitidos
        config.setAllowCredentials(true); // Permitir credenciais

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);  // Registra a configuração de CORS para todas as rotas
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();  // Gerenciador de autenticação
    }
}
