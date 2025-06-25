package de.fh_dortmund.swt2.backend.controller;

import de.fh_dortmund.swt2.backend.service.RegistrationService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fh_dortmund.swt2.backend.model.AppUser;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }
    
    @PostMapping("/register")
    public AppUser createUser(@Valid @RequestBody AppUser appUser){
        return registrationService.saveUser(appUser);
    }
    
}

