package de.fh_dortmund.swt2.backend.service;
import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegistrationService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(AppUser appUser) {
        // Prüfen, ob E-Mail oder Handynummer schon vergeben ist
        if (appUserRepository.existsByEmail(appUser.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-Mail wird bereits verwendet.");
        }

        if (appUserRepository.existsByPhonenumber(appUser.getPhoneNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Handynummer wird bereits verwendet.");
        }

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

        appUserRepository.save(appUser);
    }
}
