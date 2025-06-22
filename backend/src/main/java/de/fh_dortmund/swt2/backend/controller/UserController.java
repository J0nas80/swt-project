package de.fh_dortmund.swt2.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import de.fh_dortmund.swt2.backend.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
    
    private final UserService userService;
	
	@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



}
