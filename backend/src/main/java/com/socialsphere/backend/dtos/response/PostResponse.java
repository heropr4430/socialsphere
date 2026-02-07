package com.socialsphere.backend.dtos.response;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class PostResponse {
    private Long id;
    private String content;

    private UserResponse author;

    private int likeCount;
    private int commentCount;

    private LocalDateTime createdAt;
}
