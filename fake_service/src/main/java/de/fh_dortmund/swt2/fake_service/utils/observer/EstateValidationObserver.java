package de.fh_dortmund.swt2.fake_service.utils.observer;

import org.springframework.stereotype.Component;

@Component
public class EstateValidationObserver implements IObserver {

	@Override
	public void update(String message) {
		System.out.println("message received: " + message);
	}

}
