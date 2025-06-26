package de.fh_dortmund.swt2.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.fh_dortmund.swt2.backend.dto.EstateDto;
import de.fh_dortmund.swt2.backend.model.Address;
import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.model.Estate;
import de.fh_dortmund.swt2.backend.repository.EstateRepository;

@Service
public class EstateService {

    private final EstateRepository estateRepository;
    private final AppUserService appUserService;

    public EstateService(EstateRepository estateRepository, AppUserService appUserService) {
        this.estateRepository = estateRepository;
        this.appUserService = appUserService;
    }


    public Estate saveEstate(EstateDto estateDto, String token) {
        // Address erstellen, landlord(AppUser) durch token finden und daraus das Estate erstellen und speichern
        Address address = new Address(estateDto.getStreet(), estateDto.getHouseNumber(), estateDto.getPostalCode(),
                estateDto.getCity(), estateDto.getCountry());

        AppUser landlord = appUserService.getUserFromToken(token);

        Estate estate = new Estate(estateDto.getArea(), estateDto.getRoomCount(), estateDto.getDescription(),
                estateDto.getRentCold(), estateDto.getRentWarm(), address, landlord);

        return estateRepository.save(estate);
    }

    public List<Estate> getAllEstates() {
        return estateRepository.findAll();
    }

}
