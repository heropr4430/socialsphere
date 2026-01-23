package com.socialsphere.backend.controllers;

import com.socialsphere.backend.dtos.UserDTO;
import com.socialsphere.backend.dtos.request.RegisterRequest;
import com.socialsphere.backend.dtos.response.UserResponse;
import com.socialsphere.backend.mapper.UserMapper;
import com.socialsphere.backend.models.User;
import com.socialsphere.backend.repositories.UserRepository;
import com.socialsphere.backend.services.UserService;
import com.socialsphere.backend.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest registerRequest){
            User postUser = userService.register(registerRequest);

        return ResponseEntity.ok(postUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(UserMapper.toResponse(user));
        }catch (Exception ex){
            return  ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping("/me")
    public UserResponse me() {
        String username = SecurityUtils.getCurrentUsername();
        return userService.getUserByUsername(username);
    }

}
