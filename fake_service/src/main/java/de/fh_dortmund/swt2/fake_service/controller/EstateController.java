package de.fh_dortmund.swt2.fake_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.fh_dortmund.swt2.fake_service.exception.AppUserNotFoundException;
import de.fh_dortmund.swt2.fake_service.model.Estate;
import de.fh_dortmund.swt2.fake_service.service.EstateService;
import de.fh_dortmund.swt2.fake_service.utils.messaging.MqttSender;

@RestController
@RequestMapping("/api/Estate")
public class EstateController {
	
	@Autowired
	private MqttSender mqttPublisher;

	@Autowired
	private EstateService estateService;

	@GetMapping("/create/random")
	Estate createRandom() throws AppUserNotFoundException, Exception
	{
		return estateService.createRandomEstate();
	}

	@GetMapping("/test/mqtt")
	void createMqttTest()
	{
		System.out.println("request received");
		mqttPublisher.publishMessage("Estate", "test request ausgelöst");
		System.out.println("message send");
	}
}
