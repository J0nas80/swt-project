package de.fh_dortmund.swt2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.fh_dortmund.swt2.backend.model.AppUser;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhonenumber(String phonenumber);

    Optional<AppUser> findByEmail(String email);
}
