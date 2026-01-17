package com.socialsphere.backend.mapper;

import com.socialsphere.backend.dtos.response.UserResponse;
import com.socialsphere.backend.models.User;

public class UserMapper {
    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail()
        );
    }
}
