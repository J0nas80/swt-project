package de.fh_dortmund.swt2.fake_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.fh_dortmund.swt2.fake_service.utils.messaging.MqttSubscriber;
import de.fh_dortmund.swt2.fake_service.utils.observer.EstateValidationObserver;
import jakarta.annotation.PostConstruct;

@Component
public class MqttMonitor {
	@Autowired
	private MqttSubscriber mqttSub;

	@Autowired
	private EstateValidationObserver observer;

	@PostConstruct
	public void init() {
		mqttSub.registerObserver(observer);
		mqttSub.subscribeMessage("Estate");

		System.out.println("Subscribe Estate");
	}
}
