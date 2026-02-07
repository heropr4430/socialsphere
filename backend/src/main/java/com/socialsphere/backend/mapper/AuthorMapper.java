package com.socialsphere.backend.mapper;

import com.socialsphere.backend.dtos.response.AuthorResponse;
import com.socialsphere.backend.models.User;

public class AuthorMapper {
    public static AuthorResponse toResponse(User user) {
        return new AuthorResponse(
                user.getId(),
                user.getUsername(),
                user.getAvatar()
        );
    }
}
