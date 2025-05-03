package br.net.villeverbes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Permite acesso a todos os endpoints da API
                .allowedOrigins("http://localhost:4200")  // Permite requisições do front-end
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Permite esses métodos HTTP
                .allowedHeaders("*")  // Permite qualquer cabeçalho
                .allowCredentials(true);  // Permite o envio de cookies ou credenciais
    }
}
