package com.socialsphere.backend.dtos.response;

import com.socialsphere.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private String fullName;
    private String email;
}
