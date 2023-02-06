package com.sgiasia.javaspringboot.springdemorestapi.controllers;

import com.sgiasia.javaspringboot.springdemorestapi.helpers.JwtHelper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
    private JwtHelper jwtHelper = new JwtHelper();

    @GetMapping("/encodejwt")
    public String encodeJwt(@RequestParam String userName,@RequestParam String password,String role){
        UserDetails user =
                User.withUsername(userName)
                        .password("{noop}" + password)
                        .roles(role.toUpperCase())
                        .build();
        return jwtHelper.generateToken(user);
    }

    @GetMapping("/decodejwt")
    public String decodeJwt(@RequestParam String token){
        String userName = jwtHelper.getUsernameFromToken(token);
        return userName;
    }

}
