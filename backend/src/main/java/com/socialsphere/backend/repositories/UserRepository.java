package com.socialsphere.backend.repositories;

import com.socialsphere.backend.dtos.UserDTO;
import com.socialsphere.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User save(UserDTO userDTO);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
