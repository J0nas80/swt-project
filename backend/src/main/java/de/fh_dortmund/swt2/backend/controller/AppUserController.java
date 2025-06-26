package de.fh_dortmund.swt2.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.model.Estate;
import de.fh_dortmund.swt2.backend.service.AppUserService;

// Klasse ist aktuell eigentlich unnnötig

@RestController
@RequestMapping("api/user")
public class AppUserController {
    
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public ResponseEntity<?> getUser(){
        // todo: userdaten zurückgeben
        return null;
    }

    // Gibt Estates der saved-Liste zurück
    //@GetMapping("/saved")
    public ResponseEntity<?> getSavedEstates(){
        //Todo
        return null;
    }


}
