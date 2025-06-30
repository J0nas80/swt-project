package de.fh_dortmund.swt2.backend.service;

import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.repository.AppUserRepository;
import de.fh_dortmund.swt2.backend.security.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;


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

    
}
