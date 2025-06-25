package de.fh_dortmund.swt2.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import de.fh_dortmund.swt2.backend.dto.LoginDto;
import de.fh_dortmund.swt2.backend.service.AppUserService;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AppUserService appUserService;

    public LoginController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginRequest) {
        try{
            String token = appUserService.login(loginRequest);
            // gibt den Token als JSON zurück
            return ResponseEntity.ok(Map.of("token", token));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Benutzer nicht gefunden");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}


