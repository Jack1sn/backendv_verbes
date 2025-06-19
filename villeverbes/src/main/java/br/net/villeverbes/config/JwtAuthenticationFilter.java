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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.lang.NonNull; // Importando @NonNull para validação de nullidade

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "meusegredoaqui";

    private final UserDetailsService userDetailsService;

    // Lista de rotas públicas, mapeando método + path
    private static final List<PublicEndpoint> PUBLIC_ENDPOINTS = List.of(
        new PublicEndpoint("POST", "/auth/login"),
        new PublicEndpoint("POST", "/auth/enviar-senha"),
        new PublicEndpoint("POST", "/jogador/autocadastro"),
        new PublicEndpoint("POST", "/email/simples"),
        new PublicEndpoint("GET", "/ajuda"),
        new PublicEndpoint("GET", "/ajuda/tem-nova-mensagem"),
        new PublicEndpoint("POST", "/ajuda"),
        new PublicEndpoint("GET", "/admin/colaboradores"),
        new PublicEndpoint("GET", "/usuario/colaboradores"),
        new PublicEndpoint("GET", "/usuario/colaborador"),
        new PublicEndpoint("GET", "/usuario/jogadores"),
        new PublicEndpoint("PUT", "/usuario/jogadores/**"),
        new PublicEndpoint("POST", "/usuario/colaborador"),
        new PublicEndpoint("PUT", "/usuario/colaborador"),
        new PublicEndpoint("DELETE", "/usuario/colaboradores"),
        new PublicEndpoint("GET", "/api/frases-casa"),
        new PublicEndpoint("GET", "/api/frases-casa/"), // pra caso venha com /
        new PublicEndpoint("GET", "/api/frases-casa/**"),
        new PublicEndpoint("GET", "/api/pronomes"),
        new PublicEndpoint("GET", "/api/verbos"),
        new PublicEndpoint("GET", "/api/tempos"),
        new PublicEndpoint("GET", "/api/complementos"),
        new PublicEndpoint("POST", "/api/pronomes"),
        new PublicEndpoint("POST", "/api/verbos"),
        new PublicEndpoint("POST", "/api/tempos"),
        new PublicEndpoint("POST", "/api/complementos"),
      new PublicEndpoint("POST", "/api/jogo/{usuarioId}"),
        new PublicEndpoint("GET", "/api/jogo/{usuarioId}"),
        new PublicEndpoint("GET", "/api/jogo/{usuarioId}/{jogoId}"),
        new PublicEndpoint("POST", "/api/jogo/{usuarioId}/{jogoId}"),
        new PublicEndpoint("PUT", "/api/jogo/{usuarioId}/{jogoId}"),
        new PublicEndpoint("DELETE", "/api/jogo/{usuarioId}/{jogoId}")
    );

    public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();

        // Verifica se rota + método são públicos (não precisam de token)
        if (isPublicEndpoint(method, path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Pega o token do header Authorization
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT não encontrado ou inválido");
            return;
        }

        try {
            String jwt = token.substring(7);

            JWTVerifier verifier = JWT.require(com.auth0.jwt.algorithms.Algorithm.HMAC256(SECRET_KEY))
                    .withIssuer("villeverbesAPI")
                    .build();

            DecodedJWT decodedJWT = verifier.verify(jwt);

             System.out.println("Token válido, usuário: " + decodedJWT.getSubject());

            if (decodedJWT.getExpiresAt().before(new Date())) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expirado");
                return;
            }

            String username = decodedJWT.getSubject();

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request, response);

        } catch (JWTVerificationException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT inválido: " + e.getMessage());
        }
    }

    private boolean isPublicEndpoint(String method, String path) {
        return PUBLIC_ENDPOINTS.stream().anyMatch(endpoint -> {
            if (!endpoint.method.equalsIgnoreCase(method)) {
                return false;
            }
            if (endpoint.path.endsWith("/**")) {
                String basePath = endpoint.path.replace("/**", "");
                return path.startsWith(basePath);
            }
             // Verifica se o path é igual ou se o path tem parâmetros variáveis, como {usuarioId}
          return endpoint.path.equals(path) || path.matches(endpoint.path.replace("{usuarioId}", "\\d+").replace("{jogoId}", "\\d+"));
            //return endpoint.path.equals(path);
        });
    }

    // Classe auxiliar para mapear método + path
    private static class PublicEndpoint {
        String method;
        String path;

        PublicEndpoint(String method, String path) {
            this.method = method;
            this.path = path;
        }
    }
}
