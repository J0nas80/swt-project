package de.fh_dortmund.swt2.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import de.fh_dortmund.swt2.backend.model.User;
import de.fh_dortmund.swt2.backend.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Registrierung
    public void register(User user) {
        // Prüfen, ob E-Mail oder Handynummer schon vergeben ist
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-Mail wird bereits verwendet.");
        }
        if(userRepository.existsByPhoneNumber(user.getPhoneNumber())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Handynummer wird bereits verwendet.");
        }
        userRepository.save(user);
    }

    // Nutzer aus Token zurückgeben 
    public User getUserFromToken(String token) {
        // TODO
        // Token entschlüsseln, Benutzer-ID extrahieren, User zurückgeben
        throw new UnsupportedOperationException("Unimplemented method 'getUserFromToken'");
    }
    
}
