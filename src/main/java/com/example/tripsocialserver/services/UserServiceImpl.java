package com.example.tripsocialserver.services;



import com.example.tripsocialserver.models.User;
import com.example.tripsocialserver.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to database.", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        log.info("Deleting user with the id {}.", id);
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(long id) {
        log.info("Fetching user with the id {}.", id);
        return userRepository.findById(id);
    }

//    @Override
//    public User findByUsername(String username) {
//        List<User> userToReturn = new ArrayList<>();
//        for(User user : getAllUsers()) {
//            if (user.getUsername().equals(username)) {
//                userToReturn.add(user);
//            }
//        }
//        return userToReturn.get(0);
//    }
//
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Fetching all users.");
        return userRepository.findAll();
    }
}
