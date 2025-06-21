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
            .csrf(csrf -> csrf.disable()) // Desabilitar CSRF
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS
            .authorizeHttpRequests(auth -> auth
                // Libera CORS preflight
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Endpoints públicos (sem autenticação)
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/enviar-senha").permitAll()
                .requestMatchers("/jogador/autocadastro").permitAll()
                .requestMatchers("/email/simples").permitAll()
                .requestMatchers("/ajuda").permitAll()
                .requestMatchers("/ajuda/tem-nova-mensagem").permitAll()
                .requestMatchers("/admin/colaboradores").permitAll()
                .requestMatchers("/usuario/colaboradores").permitAll()
                .requestMatchers("/usuario/colaborador").permitAll()
                .requestMatchers("/usuario/visualizar-jogadores").permitAll()
                .requestMatchers("/usuario/jogadores").permitAll()
                .requestMatchers("/usuario/jogadores/{id}/ativo").permitAll()
                .requestMatchers("/api/frases-casa/**").permitAll()
                .requestMatchers("/api/pronomes").permitAll()
                .requestMatchers("/api/verbos").permitAll()
                .requestMatchers("/api/tempos").permitAll()
                .requestMatchers("/api/complementos").permitAll()

                // Liberação completa dos métodos para o endpoint /api/jogo/**
                .requestMatchers(HttpMethod.GET, "/api/jogo/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/jogo/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/jogo/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/jogo/**").permitAll()
                 .requestMatchers(HttpMethod.POST, "/api/jogo").permitAll()
                // Outras rotas protegidas
                .requestMatchers(HttpMethod.PUT, "/usuario/colaboradores").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/usuario/colaboradores").authenticated()
                .requestMatchers(HttpMethod.PUT, "/usuario/jogadores").authenticated()
                .requestMatchers(HttpMethod.GET, "/usuario/colaboradores/**").authenticated()

                // Permitir PUT/GET detalhado de jogadores mesmo sem autenticação
                .requestMatchers(HttpMethod.PUT, "/usuario/jogadores/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuario/jogadores/**").permitAll()

                // Todo o restante exige autenticação
                .anyRequest().authenticated()
            )
            // Filtro JWT antes da autenticação padrão
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .httpBasic(httpBasic -> {}) // Desabilita HTTP Basic
            .formLogin(form -> form.disable()) // Desabilita formulário de login
            .logout(logout -> logout.disable()) // Desabilita logout
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint((req, res, authException) ->
                    res.sendError(401, "Usuário não autorizado. Forneça um token JWT válido."))
            );

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
