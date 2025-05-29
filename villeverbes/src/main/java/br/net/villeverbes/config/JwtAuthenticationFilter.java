package br.net.villeverbes.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Filtro que intercepta as requisições para verificar e autenticar o token JWT.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Chave secreta usada para assinar/verificar o JWT
    private static final String SECRET_KEY = "meusegredoaqui";

    // Rotas públicas que não exigem autenticação
    private static final List<String> PUBLIC_PATHS = List.of(
        "/auth/login",
        "/auth/enviar-senha",
        "/jogador/autocadastro",
        "/email/simples",
        "/ajuda-emails",
        "/admin/colaboradores",
        "/usuario/colaboradores",
        "/usuario/colaborador"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        // Se for uma rota pública, segue a requisição normalmente
        if (isPublicPath(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Recupera o token do cabeçalho Authorization
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT não encontrado ou inválido");
            return;
        }

        try {
            // Remove o prefixo "Bearer "
            String jwt = token.substring(7);

            // Cria o verificador de token com a chave secreta
            JWTVerifier verifier = JWT.require(com.auth0.jwt.algorithms.Algorithm.HMAC256(SECRET_KEY))
                .withIssuer("SeuIssuerAqui")  // Se você usar um "issuer" (facultativo)
                .build();
            
            // Verifica o token JWT
            DecodedJWT decodedJWT = verifier.verify(jwt);

            // Verifica se o token expirou
            if (decodedJWT.getExpiresAt().before(new Date())) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expirado");
                return;
            }

            // Recupera o "subject" (usuário) do token
            String username = decodedJWT.getSubject();

            // Cria o token de autenticação
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(username, null, null);

            // Adiciona os detalhes da requisição ao contexto de autenticação
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Define a autenticação no contexto de segurança do Spring
            SecurityContextHolder.getContext().setAuthentication(authToken);

            // Continua a cadeia de filtros
            filterChain.doFilter(request, response);

        } catch (JWTVerificationException e) {
            // Captura o erro de verificação do JWT e retorna um erro adequado
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT inválido: " + e.getMessage());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erro ao autenticar com o token JWT: " + e.getMessage());
        }
    }

    /**
     * Verifica se a requisição é para uma rota pública (que não exige autenticação).
     */
    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }
}
