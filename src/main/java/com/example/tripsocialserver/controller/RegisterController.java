package com.example.tripsocialserver.controller;

import com.example.tripsocialserver.models.User;
import com.example.tripsocialserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "http://localhost:5173/")
public class RegisterController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(){
        return "Register Page!";
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Sends 201 status, new resource has been created.
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/register").toUriString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());
        user.setFirstName(user.getFirstName());
        //try just user
        user.setRoles("ROLE_USER");

        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
}
