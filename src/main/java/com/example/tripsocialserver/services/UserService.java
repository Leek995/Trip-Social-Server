package com.example.tripsocialserver.services;


import com.example.tripsocialserver.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User saveUser(User user);
    public void deleteUserById(long id);
    public Optional<User> findById(long id);

    public List<User> getAllUsers();
    public Optional<User> findByUsername(String username);
}
