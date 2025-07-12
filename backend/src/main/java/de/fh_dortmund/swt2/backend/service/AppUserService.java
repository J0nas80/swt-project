package de.fh_dortmund.swt2.backend.service;

import de.fh_dortmund.swt2.backend.dto.UserProfileDto;
import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.model.Estate;
import de.fh_dortmund.swt2.backend.repository.AppUserRepository;
import de.fh_dortmund.swt2.backend.repository.EstateRepository;
import de.fh_dortmund.swt2.backend.security.AppUserDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final EstateRepository estateRepository;


    public AppUserService(AppUserRepository appUserRepository, EstateRepository estateRepository) {
        this.appUserRepository = appUserRepository;
        this.estateRepository = estateRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Benutzer mit E-Mail nicht gefunden: " + email));
        return new AppUserDetails(user);
    }

    // Gibt zu Email passenden Nutzer
    public AppUser getByEmail(String email) {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Benutzer nicht gefunden: " + email));
    }


    @Transactional(readOnly = true) // Added for consistency and potential future lazy access
    public UserProfileDto getUserProfile(AppUser user) {
        // Re-fetch the user to ensure it's managed within this transaction
        AppUser managedUser = appUserRepository.findById(user.getId())
                                .orElseThrow(() -> new EntityNotFoundException("User with ID " + user.getId() + " not found for profile."));
        return new UserProfileDto(managedUser);
    }

    // Fügt Estate (anhand id) in saved-Liste hinzu
    @Transactional // <-- CRITICAL: This method MUST be transactional
    public void saveEstate(AppUser user, Long id) {
        // 1. Fetch the Estate within this transaction
        Estate estate = estateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estate mit ID " + id + " nicht gefunden"));
        
        // 2. Re-fetch the AppUser within this transaction to get a "managed" entity
        // The 'user' object passed into this method might be detached from the current session.
        AppUser managedUser = appUserRepository.findById(user.getId())
                                .orElseThrow(() -> new EntityNotFoundException("User with ID " + user.getId() + " not found for saving estate."));

        // 3. Perform the operation on the managed entity
        managedUser.saveEstate(estate); // This will now work as 'managedUser' is in an active session

        // 4. Save the managed user to persist the changes to the 'saved' collection
        appUserRepository.save(managedUser);
    }

    // Gibt die saved-liste des Users zurück
    @Transactional(readOnly = true) // <-- IMPORTANT: Add this for read operations on lazy collections
    public List<Estate> getSaved(AppUser user) {
        // Re-fetch the user to ensure 'saved' collection is initialized within the transaction
        AppUser managedUser = appUserRepository.findById(user.getId())
                                .orElseThrow(() -> new EntityNotFoundException("User with ID " + user.getId() + " not found for retrieving saved estates."));
        return managedUser.getSaved(); // This will now lazily load the collection within the transaction
    }

    // Gibt die history-liste des Users zurück
    @Transactional(readOnly = true) // <-- IMPORTANT: Add this for read operations on lazy collections
    public List<Estate> getHistory(AppUser user) {
        // Re-fetch the user to ensure 'history' collection is initialized within the transaction
        AppUser managedUser = appUserRepository.findById(user.getId())
                                .orElseThrow(() -> new EntityNotFoundException("User with ID " + user.getId() + " not found for retrieving history."));
        return managedUser.getHistory(); // This will now lazily load the collection within the transaction
    }

    // Gibt die realEstates-liste des Users zurück
    @Transactional(readOnly = true) // <-- IMPORTANT: Add this for read operations on lazy collections
    public List<Estate> getRealEstates(AppUser user) {
        // Re-fetch the user to ensure 'realEstates' collection is initialized within the transaction
        AppUser managedUser = appUserRepository.findById(user.getId())
                                .orElseThrow(() -> new EntityNotFoundException("User with ID " + user.getId() + " not found for retrieving real estates."));
        return managedUser.getRealEstates(); // This will now lazily load the collection within the transaction
    }

}
