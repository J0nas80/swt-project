package de.fh_dortmund.swt2.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import de.fh_dortmund.swt2.backend.dto.LoginRequest;
import de.fh_dortmund.swt2.backend.service.AppUserService;
import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.security.JwtUtil;
import java.util.Map;  
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginController(AppUserService appUserService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            AppUser user = appUserService.getByEmail(loginRequest.getEmail());

            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falsches Passwort");
            }

            String token = jwtUtil.generateToken(user);

            // gibt den Token als JSON zurück
            return ResponseEntity.ok(Map.of("token", token));

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Benutzer nicht gefunden");
        }
    }
}


