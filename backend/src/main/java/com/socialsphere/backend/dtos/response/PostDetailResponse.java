package com.socialsphere.backend.dtos.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Builder
public class PostDetailResponse {
    // Post info
    private Long id;
    private String content;
    private LocalDateTime createdAt;

    // Author
    private AuthorResponse author;

    // Stats
    private int likeCount;
    private int commentCount;

    // Current user state
    private boolean likedByCurrentUser;

    // Comments
    private List<CommentResponse> comments;
}
