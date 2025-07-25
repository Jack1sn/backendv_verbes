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
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                // Libera CORS preflight
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Endpoints públicos (sem autenticação)
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/enviar-senha").permitAll()
                .requestMatchers("/jogador/autocadastro").permitAll()
                .requestMatchers("/email/simples").permitAll()
                .requestMatchers("/ajuda").permitAll()
                .requestMatchers("/ajuda/{id}/resposta").permitAll()
                .requestMatchers("/ajuda/tem-nova-mensagem").permitAll()

                .requestMatchers("/admin/colaboradores").permitAll()
                .requestMatchers("/usuario/colaboradores").permitAll()
                .requestMatchers("/usuario/colaborador").permitAll()
                .requestMatchers("/usuario/visualizar-jogadores").permitAll()
                .requestMatchers("/usuario/jogadores").permitAll()
                .requestMatchers("/usuario/jogadores/{id}/ativo").permitAll()

                .requestMatchers("/api/pronomes").permitAll()
                .requestMatchers("/api/verbos").permitAll()
                .requestMatchers("/api/tempos").permitAll()
                .requestMatchers("/api/complementos").permitAll()
                .requestMatchers("/api/auth/redefinir-senha").permitAll()

                // Endpoints de frases públicos (GET, POST, PUT)
                .requestMatchers(HttpMethod.GET, "/api/frases").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/frases/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/frases/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/frases/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/frases/").permitAll() 
                .requestMatchers(HttpMethod.DELETE, "/api/frases/{id}").permitAll()  // Permitir para qualquer usuário

                // Endpoints de jogo públicos
                .requestMatchers(HttpMethod.GET, "/api/jogos/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/jogos/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/jogos/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/jogos/**").permitAll()
                 .requestMatchers("/api/selos/usuario/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/s").permitAll()
                 .requestMatchers("/api/selos/**").permitAll() 

                // Endpoints protegidos (necessita autenticação)
                .requestMatchers(HttpMethod.PUT, "/usuario/colaboradores").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/usuario/colaboradores").authenticated()
                .requestMatchers(HttpMethod.PUT, "/usuario/jogadores").authenticated()
                .requestMatchers(HttpMethod.GET, "/usuario/colaboradores/**").authenticated()

                // Permitir PUT/GET detalhado de jogadores mesmo sem autenticação
                .requestMatchers(HttpMethod.PUT, "/usuario/jogadores/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuario/jogadores/**").permitAll()

                // Qualquer outra requisição exige autenticação
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .httpBasic(httpBasic -> {}) // desabilita HTTP Basic
            .formLogin(form -> form.disable()) // desabilita login por formulário
            .logout(logout -> logout.disable()) // desabilita logout
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
        config.setAllowedOrigins(List.of("http://localhost:4200", "https://villeverbes.vercel.app"));
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
