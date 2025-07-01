package de.fh_dortmund.swt2.fake_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.fh_dortmund.swt2.fake_service.model.EstateValidationObserver;
import de.fh_dortmund.swt2.fake_service.utils.messaging.MqttImpl;
import jakarta.annotation.PostConstruct;

@Component
public class MqttMonitor {
	@Autowired
	private MqttImpl mqttSub;

	@Autowired
	private EstateValidationObserver observer;

	@PostConstruct
	public void init() {
		mqttSub.subscribeMessage("Estate", observer);
		System.out.println("Subscribe Estate");
	}
}
