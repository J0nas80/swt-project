package de.fh_dortmund.swt2.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.service.AppUserService;

@RestController
@RequestMapping("api/user")
public class AppUserController {
    
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    /* Todo: Die ist vermutlich nicht mehr nötig wegen RegistrationController (?)
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUser appUser){
        appUserService.register(appUser);
        return ResponseEntity.ok().build();
    }*/

    // Todo: Das mit den Tokens müssen wir nochmal besprechen
    @GetMapping
    public ResponseEntity<?> getUserFromToken(@RequestHeader("Authorization") String token){
        AppUser appUser = appUserService.getUserFromToken(token);
        return ResponseEntity.ok(appUser);
    }

}
