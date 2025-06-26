package de.fh_dortmund.swt2.fake_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fh_dortmund.swt2.fake_service.model.Estate;

public interface EstateRepository extends JpaRepository<Estate, Long> {

}
