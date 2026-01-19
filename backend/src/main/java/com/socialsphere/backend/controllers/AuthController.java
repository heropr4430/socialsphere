package com.socialsphere.backend.controllers;

import com.socialsphere.backend.dtos.request.LoginRequest;
import com.socialsphere.backend.dtos.request.RegisterRequest;
import com.socialsphere.backend.dtos.response.AuthResponse;
import com.socialsphere.backend.dtos.response.UserResponse;
import com.socialsphere.backend.mapper.UserMapper;
import com.socialsphere.backend.models.User;
import com.socialsphere.backend.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request) {
        User user = userService.register(request);
        return UserMapper.toResponse(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        String token = null;
        try {
            token = userService.login(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new AuthResponse(token);
    }

}
