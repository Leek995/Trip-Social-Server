package com.example.tripsocialserver;

import com.example.tripsocialserver.models.User;
import com.example.tripsocialserver.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TripSocialServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripSocialServerApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository users, PasswordEncoder encoder){
//        return args -> {
//            users.save(new User("user", encoder.encode("password"), "ROLE_USER"));
//            users.save(new User("admin", encoder.encode("password"), "ROLE_ADMIN"));
//        };
//    }

}
