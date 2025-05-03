package br.net.villeverbes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Desabilita CSRF (geralmente para APIs REST)
            .csrf(csrf -> csrf.disable())

            // Configuração das autorizações de requisições
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/login", "/home").permitAll()  // Permite acesso sem autenticação a essas rotas
                .anyRequest().authenticated()  // Exige autenticação para todas as outras requisições
            )
            
            // Configuração de login explícita
            .formLogin(form -> form
                .loginPage("/login")  // Página de login personalizada (caso você tenha uma)
                .permitAll()  // Permite acesso sem autenticação à página de login
                .defaultSuccessUrl("/home", true)  // Redireciona após login bem-sucedido
            )

            // Configuração de logout
            .logout(logout -> logout
                .permitAll()  // Permite logout sem autenticação
                .logoutUrl("/logout")  // URL para logout
                .clearAuthentication(true)  // Limpa a autenticação ao fazer logout
            );

        return http.build();  // Cria a configuração
    }

    // Bean para o PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
