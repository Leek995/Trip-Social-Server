package com.example.tripsocialserver.controller;

import com.example.tripsocialserver.services.TokenService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;

@RestController
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/api/auth/token")
    public String token(Authentication authentication){
        String message = "kfasjjfs";
        String myJSON = """
                {
                    "jwt":""" +"\"" + tokenService.generateToken(authentication) + "\"" + """ 
                }
                """;
        return myJSON;
//        return tokenService.generateToken(authentication);
    }
}
