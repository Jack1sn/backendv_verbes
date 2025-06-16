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
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/frases-casa", "/api/frases-casa/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/frases-casa").authenticated()
                .requestMatchers(HttpMethod.POST, "/ajuda").permitAll()
                .requestMatchers("/usuario/colaborador").permitAll()
                  .requestMatchers(HttpMethod.GET, "/ajuda/tem-nova-mensagem").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/usuario/colaborador").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/usuario/colaboradores").permitAll()
                 .requestMatchers(HttpMethod.PUT, "/usuario/colaboradores").permitAll()
                  .requestMatchers(HttpMethod.PUT, "/usuario/jogadores").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/pronomes", "/api/verbos", "/api-tempos", "/api/complementos").permitAll()
                .requestMatchers(
                    "/auth/login",
                    "/auth/enviar-senha",
                    "/jogador/autocadastro",
                    "/email/simples",
                    "/ajuda",
                    "/admin/colaboradores",
                    "/usuario/colaboradores",
                    "/usuario/colaborador",
                     "/usuario/jogadores",
                     "/usuario/jogadores/**",
                    "/api/pronomes",
                    "/api/verbos",
                    "/api/tempos",
                    "/api/complementos"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .httpBasic(httpBasic -> {})
            .formLogin(form -> form.disable())
            .logout(logout -> logout.disable())
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendError(401, "Usuário não autorizado. Por favor, forneça um token válido.");
                })
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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
