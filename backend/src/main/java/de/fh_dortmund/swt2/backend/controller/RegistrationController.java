package de.fh_dortmund.swt2.backend.controller;

import de.fh_dortmund.swt2.backend.service.AppUserService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fh_dortmund.swt2.backend.model.AppUser;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

    private final AppUserService appUserService;

    public RegistrationController(AppUserService appUserService){
        this.appUserService = appUserService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody AppUser appUser){
        return ResponseEntity.ok(appUserService.saveUser(appUser));
    }
    
}

