package de.fh_dortmund.swt2.fake_service.exception;



public class AppUserNotFoundException extends RuntimeException {
	
	public AppUserNotFoundException() {
		super("No AppUser Found");
	}

}
