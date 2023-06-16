package com.example.tripsocialserver.controller;

import com.example.tripsocialserver.models.User;
import com.example.tripsocialserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    // Mapping to READ/VIEW all users.

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public Optional<User> loggedInUser(Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        return user;
    }

    // Mapping to READ/VIEW single user.

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return ResponseEntity.ok().body(userService.findById(id).orElseThrow(() -> new UsernameNotFoundException("user with id " + id + "not found.")));
    }


    // Mapping to UPDATE/EDIT user information.

    @PutMapping("/users/{id}/edit-profile")
    public ResponseEntity<User> updateUser(@RequestBody User userToEdit, @PathVariable long id) {
        return ResponseEntity.ok().body(userService.findById(id)
                .map(user -> {
                    user.setUsername(userToEdit.getUsername());
                    user.setPassword(userToEdit.getPassword());
                    user.setRoles("ROLE_USER");
                    // If user sends no favorite team, assign the Cowboys as their favorite team.

                    return userService.saveUser(user);
                }).orElseThrow(() -> new UsernameNotFoundException("user with id " + id + "not found.")));
    }


    // Mapping to DELETE user entity.
    @DeleteMapping("/users/{id}/delete")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
