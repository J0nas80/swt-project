package de.fh_dortmund.swt2.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JWTAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Define paths that should be publicly accessible without JWT validation.
        // This must match the paths you've set to permitAll() in your SecurityConfig.
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/api/auth/register") || requestURI.startsWith("/api/auth/login")) {
            // If it's a public path, simply proceed down the filter chain.
            // Spring Security's authorizeHttpRequests will then handle the permitAll() rule.
            filterChain.doFilter(request, response);
            return; // IMPORTANT: Exit this filter immediately
        }

        // For all other paths, proceed with JWT validation
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Extract the token after "Bearer "
            try {
                email = jwtUtil.extractEmail(token);
            } catch (Exception e) {
                // Token is invalid or expired – log this for debugging, but don't stop the chain here
                // If the token is invalid/expired for a protected resource, the .anyRequest().authenticated()
                // will eventually lead to a 401 Unauthorized.
                System.out.println("Invalid or expired JWT: " + e.getMessage());
            }
        }

        // If email is found from token and no authentication is currently set in the context
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // Validate the token against the user details
            if (token != null && jwtUtil.isValid(token, userDetails.getUsername())) { // Assuming isValid takes username/email
                // Create an authentication object and set it in the SecurityContext
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                // Optionally set authentication details for logging/auditing
                // authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continue the filter chain for all requests (after potential JWT processing or bypass)
        filterChain.doFilter(request, response);
    }
}
