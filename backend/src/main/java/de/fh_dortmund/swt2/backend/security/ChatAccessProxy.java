package de.fh_dortmund.swt2.backend.security;

import de.fh_dortmund.swt2.backend.security.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;

@Component
public class ChatAccessProxy {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public ChatAccessProxy(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    public Authentication verify(String token) {
       
        try {
            String email = jwtUtil.extractEmail(token);
            if (jwtUtil.isValid(token, email)) {
                AppUserDetails appUserDetails = (AppUserDetails) userDetailsService.loadUserByUsername(email);
                return new UsernamePasswordAuthenticationToken(appUserDetails, null, appUserDetails.getAuthorities());

            }
        } catch (Exception e) {
            throw new RuntimeException("Ungültiger oder abgelaufener Token: " + e.getMessage());
        }

        throw new RuntimeException("Tokenprüfung fehlgeschlagen");
    }
}
