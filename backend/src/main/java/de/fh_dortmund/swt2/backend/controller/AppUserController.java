package de.fh_dortmund.swt2.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.security.AppUserDetails;
import de.fh_dortmund.swt2.backend.service.AppUserService;

@RestController
@RequestMapping("api/user/me")
public class AppUserController {
    
    private final AppUserService appUserService;


    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    // Gibt User-Profildaten zurück (NICHT history etc.)
    @GetMapping
    public ResponseEntity<?> getUser(Authentication authentication){
        AppUser user = extractAppUser(authentication);
        return ResponseEntity.ok(appUserService.getUserProfile(user));
    }

    // Speichert Inserat in saved-Liste ab
    @PostMapping("/saved/{id}")
    public ResponseEntity<?> saveEstate(Authentication authentication, @PathVariable Long id){
        AppUser user = extractAppUser(authentication);
        appUserService.saveEstate(user, id);
        return ResponseEntity.ok().build();
    }

    // Gibt Estates der saved-Liste zurück
    @GetMapping("/saved")
    public ResponseEntity<?> getSaved(Authentication authentication){
        AppUser user = extractAppUser(authentication);
        return ResponseEntity.ok(appUserService.getSaved(user));
    }

    // Gibt Estates der history-Liste zurück
    @GetMapping("/history")
    public ResponseEntity<?> getHistory(Authentication authentication){
        AppUser user = extractAppUser(authentication);
        return ResponseEntity.ok(appUserService.getHistory(user));
    }

    // Gibt Estates der realEstates-Liste zurück
    @GetMapping("/realEstates")
    public ResponseEntity<?> getRealEstates(Authentication authentication){
        AppUser user = extractAppUser(authentication);
        return ResponseEntity.ok(appUserService.getRealEstates(user));
    }

    // Hilfsmethode  Authentication statt manuelles Token-Parsen  nutzt Spring Security Context

    private AppUser extractAppUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof AppUserDetails) {
            return ((AppUserDetails) principal).getAppUser();
        }
        throw new IllegalStateException("Ungültiger Authentifizierungszustand");
    }
}
