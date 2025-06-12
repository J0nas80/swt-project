package de.fh_dortmund.swt2.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.repository.AppUserRepository;

public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    // Registrierung
    public void register(AppUser appUser) {
        // Prüfen, ob E-Mail oder Handynummer schon vergeben ist
        if(appUserRepository.existsByEmail(appUser.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-Mail wird bereits verwendet.");
        }
        if(appUserRepository.existsByPhoneNumber(appUser.getPhoneNumber())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Handynummer wird bereits verwendet.");
        }
        appUserRepository.save(appUser);
    }

    // Nutzer aus Token zurückgeben 
    public AppUser getUserFromToken(String token) {
        // TODO
        // Token entschlüsseln, Benutzer-ID extrahieren, User zurückgeben
        throw new UnsupportedOperationException("Unimplemented method 'getUserFromToken'");
    }
    
}
