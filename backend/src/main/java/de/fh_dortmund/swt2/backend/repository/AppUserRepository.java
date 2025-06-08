package de.fh_dortmund.swt2.backend.repository;

import de.fh_dortmund.swt2.backend.model.AppUser;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}

