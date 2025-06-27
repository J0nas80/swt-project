package de.fh_dortmund.swt2.fake_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.fh_dortmund.swt2.fake_service.exception.AppUserNotFoundException;
import de.fh_dortmund.swt2.fake_service.model.Estate;
import de.fh_dortmund.swt2.fake_service.service.EstateService;

@RestController
@RequestMapping("/api/Estate")
public class EstateController {
	
	@Autowired
	private EstateService estateService;

	@GetMapping("/create/random")
	Estate createRandom() throws AppUserNotFoundException, Exception
	{
			return estateService.createRandomEstate();
	}
}
