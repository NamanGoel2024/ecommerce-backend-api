package ecommerce_backend_api.security;

import ecommerce_backend_api.entity.User;
import ecommerce_backend_api.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    public JwtFilter(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // Public APIs
        if(path.startsWith("/users/login") ||
           path.startsWith("/users/register") ||
           path.startsWith("/swagger-ui") ||
           path.startsWith("/v3/api-docs")) {

            filterChain.doFilter(request, response);

            return;
        }

        String authHeader =
                request.getHeader("Authorization");

        // Check token existence
        if(authHeader == null ||
           !authHeader.startsWith("Bearer ")) {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );

            return;
        }

        // Remove "Bearer "
        String token =
                authHeader.substring(7);

        try {

            // Validate JWT token
            Claims claims = Jwts.parser()
                    .verifyWith(JwtUtil.getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            // Extract email from token
            String email = claims.getSubject();

            // Fetch user from database
            User user =
                    userRepository.findByEmail(email);

            // Convert role into Spring Security authority
            List<SimpleGrantedAuthority> authorities =
                    List.of(
                            new SimpleGrantedAuthority(
                                    "ROLE_" + user.getRole()
                            )
                    );

            // Create authenticated user object
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            authorities
                    );

            // Store authentication in SecurityContext
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            request.setAttribute("email", email);

        } catch (Exception e) {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );

            return;
        }

        filterChain.doFilter(request, response);
    }
}