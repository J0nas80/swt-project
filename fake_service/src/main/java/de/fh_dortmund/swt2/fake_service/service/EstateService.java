package de.fh_dortmund.swt2.fake_service.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fh_dortmund.swt2.fake_service.exception.AppUserNotFoundException;
import de.fh_dortmund.swt2.fake_service.factory.EstateFactory;
import de.fh_dortmund.swt2.fake_service.model.Address;

//import com.github.javafaker;

import de.fh_dortmund.swt2.fake_service.model.AppUser;
import de.fh_dortmund.swt2.fake_service.model.Estate;
import de.fh_dortmund.swt2.fake_service.repository.AppUserRepository;
import de.fh_dortmund.swt2.fake_service.repository.EstateRepository;
import de.fh_dortmund.swt2.fake_service.utils.messaging.MqttSender;

@Service
public class EstateService {

	//private static Faker faker = new Faker();
	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private MqttSender mqttPublisher;

	@Autowired
	private EstateRepository estateRepository;

	@Autowired
	private RandomizerService randomizer;

	public Estate createRandomEstate() throws AppUserNotFoundException, Exception {
		double area = randomizer.getRandomDouble(25.0, 150.0);
		double roomCount = randomizer.getRandomInt(3, 10);
		double coldRent = area * randomizer.getRandomDouble(5.0, 20.0);
		randomizer.getRandomDouble(200.0, 1000.0);
  		//String address = "random Address" + LocalDateTime.now().toString();
		Address address = new Address("blocked", "blocked", "blocked", "blocked", "blocked");

 
		Estate e = EstateFactory.createEstate("Wohnraum der Stadt", "wohnung",  area, roomCount, "description", coldRent, address, findRandomAppUser(), "", LocalDate.now() );

		e = estateRepository.save(e);
		mqttPublisher.publishMessage("EstatePublished", Long.toString((long)e.getId()));
		return e;
	}

	private AppUser findRandomAppUser() throws AppUserNotFoundException, Exception
	{
		List<AppUser> allUsers = appUserRepository.findAll();

		if(allUsers.isEmpty())
		{
			throw new AppUserNotFoundException();
		}

		int index = randomizer.getRandomInt(allUsers.size());

		return allUsers.get(index);
	}

	public void ValidateEstate(long id) {
		Estate e = estateRepository.findById(id).orElse(null);

		if(e == null)
			return;
		
		System.out.println("Estate Found.");
		e.setValidated(true);
		estateRepository.save(e);

		System.out.println("Estate Validated");

	}
}
