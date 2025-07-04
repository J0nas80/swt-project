package de.fh_dortmund.swt2.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.fh_dortmund.swt2.backend.model.AppUser;
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
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String token){
        AppUser user = appUserService.getUserFromToken(token);
        return ResponseEntity.ok(appUserService.getUserProfile(user));
    }

    // Speichert Inserat in saved-Liste ab
    @PostMapping("/saved/{id}")
    public ResponseEntity<?> saveEstate(@RequestHeader("Authorization") String token, @PathVariable Long id){
        AppUser user = appUserService.getUserFromToken(token);
        appUserService.saveEstate(user, id);
        return ResponseEntity.ok().build();
    }

    // Gibt Estates der saved-Liste zurück
    @GetMapping("/saved")
    public ResponseEntity<?> getSaved(@RequestHeader("Authorization") String token){
        AppUser user = appUserService.getUserFromToken(token);
        return ResponseEntity.ok(appUserService.getSaved(user));
    }

    // Gibt Estates der history-Liste zurück
    @GetMapping("/history")
    public ResponseEntity<?> getHistory(@RequestHeader("Authorization") String token){
        AppUser user = appUserService.getUserFromToken(token);
        return ResponseEntity.ok(appUserService.getHistory(user));
    }

    // Gibt Estates der realEstates-Liste zurück
    @GetMapping("/realEstates")
    public ResponseEntity<?> getRealEstates(@RequestHeader("Authorization") String token){
        AppUser user = appUserService.getUserFromToken(token);
        return ResponseEntity.ok(appUserService.getRealEstates(user));
    }

}
