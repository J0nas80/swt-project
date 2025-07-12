package de.fh_dortmund.swt2.fake_service.utils.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.fh_dortmund.swt2.fake_service.service.EstateService;
import de.fh_dortmund.swt2.fake_service.utils.messaging.MqttSender;

@Component
public class EstateRequestObserver implements IObserver {

	@Autowired
	private EstateService estateService;
	@Autowired
	private MqttSender mqttSender;

	@Override
	public void update(String topic, String message) {
		System.out.println("message on "+ topic +" received: " + message);

		if(topic.equals("Estate")) {
			try {
				//Thread.sleep(10000);

				long estateId = Long.parseLong(message);
				estateService.ValidateEstate(estateId);

				mqttSender.publishMessage("ValidationResult:"+message, "Success");
			} catch(Exception e) {
				mqttSender.publishMessage("ValidationResult:"+message, "Failed: "+ e.getMessage());	
			}
		}
	}

}
