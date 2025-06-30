package de.fh_dortmund.swt2.backend.controller;

import de.fh_dortmund.swt2.backend.service.RegistrationService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fh_dortmund.swt2.backend.model.AppUser;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody AppUser appUser){
        registrationService.register(appUser);
        return ResponseEntity.ok(Map.of("message", "Benutzer erfolgreich registriert"));
    }
    
    }
    


