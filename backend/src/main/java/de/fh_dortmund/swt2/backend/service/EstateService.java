package de.fh_dortmund.swt2.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.fh_dortmund.swt2.backend.model.Estate;
import de.fh_dortmund.swt2.backend.repository.EstateRepository;

@Service
public class EstateService {
    
    private final EstateRepository estateRepository;

    public EstateService(EstateRepository estateRepository){
        this.estateRepository = estateRepository;
    }

    public void saveEstate(Estate estate) {
        estateRepository.save(estate);
    }

    public List<Estate> getAllEstates() {
        return estateRepository.findAll();
    }
    
}
