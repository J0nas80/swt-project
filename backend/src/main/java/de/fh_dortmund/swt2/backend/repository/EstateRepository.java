package de.fh_dortmund.swt2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.fh_dortmund.swt2.backend.model.Estate;

public interface EstateRepository extends JpaRepository<Estate, Long> {
    
}
