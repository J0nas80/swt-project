package de.fh_dortmund.swt2.backend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fh_dortmund.swt2.backend.dto.LoginDto;
import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.service.AuthenticationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody AppUser appUser){
        return ResponseEntity.ok(authenticationService.register(appUser));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginRequest) {
        String token = authenticationService.login(loginRequest);
        // gibt den Token als JSON zurück
        return ResponseEntity.ok(Map.of("token", token));
    }

}
