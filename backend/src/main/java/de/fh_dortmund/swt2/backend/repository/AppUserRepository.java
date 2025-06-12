package de.fh_dortmund.swt2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.fh_dortmund.swt2.backend.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
    
}