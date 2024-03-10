package com.datmt.security.jwt.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datmt.security.jwt.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicApiController {

    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        String token = userService.loginAndGenerateToken(loginRequest);
        return new LoginResponse(token);
    }

    public record LoginRequest(String username, String password) {

    }

    public record LoginResponse(String token) {

    }
}