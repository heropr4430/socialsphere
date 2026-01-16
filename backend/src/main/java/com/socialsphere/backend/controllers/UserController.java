package com.socialsphere.backend.controllers;

import com.socialsphere.backend.dtos.UserDTO;
import com.socialsphere.backend.models.User;
import com.socialsphere.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFullName());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());

        User postUser = userRepository.save(user);

        return ResponseEntity.ok(postUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userRepository.findById(userId).get());
    }

}
