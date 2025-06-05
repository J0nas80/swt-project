package de.fh_dortmund.swt2.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fh_dortmund.swt2.backend.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



}
