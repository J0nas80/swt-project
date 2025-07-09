package de.fh_dortmund.swt2.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import de.fh_dortmund.swt2.backend.dto.LoginDto;
import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.repository.AppUserRepository;
import de.fh_dortmund.swt2.backend.security.JwtUtil;

@Service
public class AuthenticationService {
    
    private AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public AuthenticationService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    public AppUser register(AppUser appUser) {
        // Neuen User abspeichern
        // Prüfen, ob E-Mail oder Handynummer schon vergeben ist
        if (appUserRepository.existsByEmail(appUser.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-Mail wird bereits verwendet.");
        }

        if (appUserRepository.existsByPhonenumber(appUser.getPhoneNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Handynummer wird bereits verwendet.");
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    public String login(LoginDto loginRequest) {
        // User finden, Passwort überprüfen und Token generieren
        AppUser user = appUserRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Benutzer nicht gefunden: " + loginRequest.getEmail()));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Falsches Passwort");
        }
        return jwtUtil.generateToken(user);
    }
    
}
