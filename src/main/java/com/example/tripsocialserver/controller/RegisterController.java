package com.example.tripsocialserver.controller;

import com.example.tripsocialserver.models.User;
import com.example.tripsocialserver.services.StorageService;
import com.example.tripsocialserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "https://ts-server-production.up.railway.app/")
public class RegisterController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Autowired
    private StorageService service;

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

    @PostMapping("/register/{id}/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable long id
    ) throws IOException {
        userService.findById(id)
                .map(user -> {
                    try {
                        user.setImageData(service.uploadImage(file));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return userService.saveUser(user);
                        });
//        User user = userService.findById(id);
//        user.setImageData(service.uploadImage(file));
//        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(file.getOriginalFilename() + "saved successfully!");
    }

    @GetMapping("/register/image/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData=service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

}
