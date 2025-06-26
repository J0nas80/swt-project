package de.fh_dortmund.swt2.backend.service;

import de.fh_dortmund.swt2.backend.dto.LoginDto;
import de.fh_dortmund.swt2.backend.dto.UserProfileDto;
import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.model.Estate;
import de.fh_dortmund.swt2.backend.repository.AppUserRepository;
import de.fh_dortmund.swt2.backend.repository.EstateRepository;
import de.fh_dortmund.swt2.backend.security.AppUserDetails;
import de.fh_dortmund.swt2.backend.security.JwtUtil;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final EstateRepository estateRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AppUserService(AppUserRepository appUserRepository, EstateRepository estateRepository,
            PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.appUserRepository = appUserRepository;
        this.estateRepository = estateRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // LOGIN UND REGISTRATION
    /*
     * Könnte man statdessen in eigene Service Klassen packen, hätten aber je nur
     * eine Methode
     * Oder vielleicht kombinieren zu AuthentifizierungController und
     * AuthentifizierungService?
     */
    public AppUser saveUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    /*
     * ALTE VERSION (stand ursprünglich in LoginController)
     * public ResponseEntity<?> login(LoginDto loginRequest) {
     * try {
     * AppUser user = appUserService.getByEmail(loginRequest.getEmail());
     * 
     * if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
     * {
     * return
     * ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falsches Passwort");
     * }
     * 
     * String token = jwtUtil.generateToken(user.getEmail());
     * 
     * // gibt den Token als JSON zurück
     * return ResponseEntity.ok(Map.of("token", token));
     * 
     * } catch (UsernameNotFoundException e) {
     * return
     * ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Benutzer nicht gefunden"
     * );
     * }
     * }
     */

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
        // Token kommt in Anfragen als "Bearer <token>", also muss "Bearer " ggf.
        // abgeschnitten werden
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        // Email aus Token extrahieren und passenden AppUser zurückgeben
        String email = jwtUtil.extractEmail(token);
        if (email == null) {
            throw new UsernameNotFoundException("Token ethält keine E-Mail");
        }
        return appUserRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Nutzer nicht gefunden: " + email));
    }

    // Gibt User-Profildaten zurück (NICHT history etc.)
    public UserProfileDto getUserProfile(AppUser user) {
        return new UserProfileDto(user);
    }

    // Fügt Estate (anhand id) in saved-Liste hinzu
    public void saveEstate(AppUser user, Long id) {
        Estate estate = estateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estate mit ID " + id + "nicht gefunden"));
        user.saveEstate(estate);
    }

    // Gibt die saved-liste des Users zurück
    public List<Estate> getSaved(AppUser user) {
        return user.getSaved();
        // Todo: Besser nur gezielte Attribute mittels Dto liefern? (z.B. id, titel, rentCold, img)
    }

    // Gibt die history-liste des Users zurück
    public List<Estate> getHistory(AppUser user) {
        return user.getHistory();
        // Todo: Besser nur gezielte Attribute mittels Dto liefern? (z.B. id, titel, rentCold, img)
    }

    // Gibt die realEstates-liste des Users zurück
    public List<Estate> getRealEstates(AppUser user) {
        return user.getRealEstates();
        // Todo: Besser nur gezielte Attribute mittels Dto liefern? (z.B. id, titel, rentCold, img)
    }

}
