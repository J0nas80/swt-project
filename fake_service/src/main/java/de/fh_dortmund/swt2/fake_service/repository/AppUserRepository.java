package de.fh_dortmund.swt2.fake_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fh_dortmund.swt2.fake_service.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

} 
