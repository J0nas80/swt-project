package de.fh_dortmund.swt2.backend.service;

import de.fh_dortmund.swt2.backend.dto.LoginDto;
import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.repository.AppUserRepository;
import de.fh_dortmund.swt2.backend.security.AppUserDetails;
import de.fh_dortmund.swt2.backend.security.JwtUtil;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // LOGIN UND REGISTRATION
    /* Könnte man statdessen in eigene Service Klassen packen, hätten aber je nur eine Methode
    Oder vielleicht kombinieren zu AuthentifizierungController und AuthentifizierungService? */ 
    public AppUser saveUser(AppUser appUser){
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    /*ALTE VERSION (stand ursprünglich in LoginController)
    public ResponseEntity<?> login(LoginDto loginRequest) {
        try {
            AppUser user = appUserService.getByEmail(loginRequest.getEmail());

            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falsches Passwort");
            }

            String token = jwtUtil.generateToken(user.getEmail());

            // gibt den Token als JSON zurück
            return ResponseEntity.ok(Map.of("token", token));

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Benutzer nicht gefunden");
        }
    }*/

    // Neue Version: Service-Logik hier, HTTP-Logik im Controller
    public String login(LoginDto loginRequest) {
            AppUser user = this.getByEmail(loginRequest.getEmail());
            
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                throw new IllegalArgumentException("Falsches Passwort");
            }
            return jwtUtil.generateToken(user.getEmail());
    }


    // SONSTIGE USER METHODEN

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

    // Nutzer aus Token zurückgeben 
    public AppUser getUserFromToken(String token) {
        // TODO
        // Token entschlüsseln, Benutzer-ID extrahieren, User zurückgeben
        throw new UnsupportedOperationException("Unimplemented method 'getUserFromToken'");
    }
}
