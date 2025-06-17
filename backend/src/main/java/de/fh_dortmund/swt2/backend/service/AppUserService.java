package de.fh_dortmund.swt2.backend.service;

import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.repository.AppUserRepository;
import de.fh_dortmund.swt2.backend.security.AppUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Benutzer mit E-Mail nicht gefunden: " + email));
        return new AppUserDetails(user);
    }

    public AppUser getByEmail(String email) {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Benutzer nicht gefunden: " + email));
    }

    // Registrierung
    public void register(AppUser appUser) {
        // Prüfen, ob E-Mail oder Handynummer schon vergeben ist
        if(appUserRepository.existsByEmail(appUser.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-Mail wird bereits verwendet.");
        }
        if(appUserRepository.existsByPhoneNumber(appUser.getPhonenumber())){
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
