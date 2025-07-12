package de.fh_dortmund.swt2.backend.utils.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.fh_dortmund.swt2.backend.model.Estate;


public class EstateValidationObserver implements IObserver {

	private Estate estateToValidate;

	public EstateValidationObserver(Estate estateToValidate)
	{
		System.out.println("Observer for Estate " + estateToValidate.getId() );
		this.estateToValidate = estateToValidate;
	}

	public Estate getEstateToValidate() {
		return estateToValidate;
	}

	public void setEstateToValidate(Estate estateToValidate) {
		this.estateToValidate = estateToValidate;
	}

	@Override
	public void update(String topic, String message) {
		System.out.println("message on "+ topic +" received: " + message);

		if(topic.equals("ValidationResult:"+estateToValidate.getId().toString())) {
			if (message.startsWith("Success")) {
	
			}
			else
			{
				// send message to Owner
			}
		}
	}

}
