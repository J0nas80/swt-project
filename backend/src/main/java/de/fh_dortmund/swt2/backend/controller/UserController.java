package de.fh_dortmund.swt2.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.fh_dortmund.swt2.backend.model.User;
import de.fh_dortmund.swt2.backend.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        userService.register(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getUserFromToken(@RequestHeader("Authorization") String token){
        User user = userService.getUserFromToken(token);
        return ResponseEntity.ok(user);
    }

}
