package com.socialsphere.backend.controllers;

import com.socialsphere.backend.dtos.request.LoginRequest;
import com.socialsphere.backend.dtos.request.RegisterRequest;
import com.socialsphere.backend.dtos.response.ApiResponse;
import com.socialsphere.backend.dtos.response.AuthResponse;
import com.socialsphere.backend.dtos.response.UserResponse;
import com.socialsphere.backend.mapper.UserMapper;
import com.socialsphere.backend.models.User;
import com.socialsphere.backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {

        String token = userService.login(request);

        return ResponseEntity.ok(token);
    }

}
