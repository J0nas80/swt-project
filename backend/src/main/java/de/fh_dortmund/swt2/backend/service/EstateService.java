package de.fh_dortmund.swt2.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.fh_dortmund.swt2.backend.dto.EstateCreateDto;
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

    public Estate saveEstate(EstateCreateDto estateDto, String token) {
        // Address erstellen, landlord(AppUser) durch token finden und daraus das Estate
        // erstellen und speichern
        Address address = new Address(estateDto.getStreet(), estateDto.getHouseNumber(), estateDto.getPostalCode(),
                estateDto.getCity(), "DE");

        AppUser landlord = appUserService.getUserFromToken(token);

        Estate estate = new Estate(estateDto.getTitel(), estateDto.getType(), estateDto.getArea(),
                estateDto.getRoomCount(), estateDto.getDescription(),
                estateDto.getRentCold(), address, landlord, estateDto.getImg(), estateDto.getAvailableFrom());

        return estateRepository.save(estate);
    }

    public List<Estate> getAllEstates() {
        return estateRepository.findAll();
    }

    public Estate getEstateById(Long id){
        return estateRepository.findById(id).orElse(null);
    }

    //Methode für exakten Filter
    public List<Estate> searchEstateByFilters(String city, Double minPrice, Double maxPrice, String type){
        return estateRepository.findByFilters(city, minPrice, maxPrice, type);
    }


}
