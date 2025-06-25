package de.fh_dortmund.swt2.fake_service.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import de.fh_dortmund.swt2.fake_service.exception.AppUserNotFoundException;

@RestControllerAdvice
public class AppUserNotFoundAdvice {

	@ExceptionHandler(AppUserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String appUserNotFoundHandler(AppUserNotFoundException ex) {
		return ex.getMessage();
	}
}
