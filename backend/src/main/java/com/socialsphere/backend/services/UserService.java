package com.socialsphere.backend.services;

import com.socialsphere.backend.dtos.request.LoginRequest;
import com.socialsphere.backend.dtos.request.RegisterRequest;
import com.socialsphere.backend.dtos.response.UserResponse;
import com.socialsphere.backend.mapper.UserMapper;
import com.socialsphere.backend.models.User;
import com.socialsphere.backend.repositories.UserRepository;
import com.socialsphere.backend.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    // Đăng ký
    public User register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

    // Login
    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword(),
                user.getAuthorities()
        );
        try {
            authenticationManager.authenticate(authenticationToken);
            return jwtProvider.generateToken(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));;
        return user;
    }

    public UserResponse getUserByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));;
        return UserMapper.toResponse(user);
    }

}
