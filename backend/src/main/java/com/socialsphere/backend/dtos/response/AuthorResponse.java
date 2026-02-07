package com.socialsphere.backend.dtos.response;

import com.socialsphere.backend.models.User;
import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Builder
public class AuthorResponse {
    private Long id;
    private String username;
    private String avatar;


}

