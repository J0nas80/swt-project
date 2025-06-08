package de.fh_dortmund.swt2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.fh_dortmund.swt2.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
    
}