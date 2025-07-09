package de.fh_dortmund.swt2.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fh_dortmund.swt2.backend.dto.EstateCreateDto;
import de.fh_dortmund.swt2.backend.model.Address;
import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.model.Estate;
import de.fh_dortmund.swt2.backend.repository.EstateRepository;
import de.fh_dortmund.swt2.backend.security.JwtUtil;
import de.fh_dortmund.swt2.backend.utils.messaging.MqttSender;
import de.fh_dortmund.swt2.backend.utils.messaging.MqttSubscriber;
import de.fh_dortmund.swt2.backend.utils.observer.EstateValidationObserver;
import de.fh_dortmund.swt2.backend.utils.observer.IObserver;

@Service
public class EstateService {

    private final EstateRepository estateRepository;

    private final AppUserService appUserService;
	  private final JwtUtil jwtUtil;
	  private final MqttSubscriber mqttSub;
	  private final MqttSender mqttSender;

    public EstateService(EstateRepository estateRepository, AppUserService appUserService, JwtUtil jwtUtil, MqttSender mqttSender, MqttSubscriber mqttSub) {
        this.estateRepository = estateRepository;
        this.appUserService = appUserService;
        this.jwtUtil = jwtUtil;
        this.mqttSub = mqttSub;
        this.mqttSender = mqttSender;
    }

    public Estate saveEstate(EstateCreateDto estateDto, String token) {
        // Address erstellen, landlord(AppUser) durch token finden und daraus das Estate
        // erstellen und speichern
        Address address = new Address(estateDto.getStreet(), estateDto.getHouseNumber(), estateDto.getPostalCode(),
                estateDto.getCity(), "DE");

        AppUser landlord = jwtUtil.getUserFromToken(token);

        Estate estate = new Estate(estateDto.getTitel(), estateDto.getType(), estateDto.getArea(),
                estateDto.getRoomCount(), estateDto.getDescription(),
                estateDto.getRentCold(), address, landlord, estateDto.getImg(), estateDto.getAvailableFrom());

        Estate e = estateRepository.save(estate);

		IObserver validationObserver = new EstateValidationObserver(e);
		mqttSub.registerObserver(validationObserver);
		mqttSub.subscribeMessage("ValidationResult:"+e.getId().toString());
		mqttSender.publishMessage("Estate", e.getId().toString());

		return e;
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
