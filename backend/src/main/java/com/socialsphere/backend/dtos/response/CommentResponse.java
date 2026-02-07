package com.socialsphere.backend.dtos.response;

import com.socialsphere.backend.mapper.AuthorMapper;
import com.socialsphere.backend.models.Comment;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Builder
public class CommentResponse {
    private Long id;
    private String content;

    private AuthorResponse author;

    private LocalDateTime createdAt;


}
