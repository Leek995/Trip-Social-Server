package com.example.tripsocialserver;

import com.example.tripsocialserver.models.User;
import com.example.tripsocialserver.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TripSocialServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripSocialServerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository users){
        return args -> {
            users.save(new User("user", "password", "ROLE_USER"));
            users.save(new User("admin", "password", "ROLE_ADMIN"));
        };
    }

}
