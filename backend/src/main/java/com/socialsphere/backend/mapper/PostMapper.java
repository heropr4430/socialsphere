package com.socialsphere.backend.mapper;


import com.socialsphere.backend.dtos.response.PostResponse;
import com.socialsphere.backend.models.Post;

public class PostMapper {

    public static PostResponse toResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getContent(),
                UserMapper.toResponse(post.getAuthor()),
                post.getLikeCount(),
                post.getCommentCount(),
                post.getCreatedAt()

        );
    }
}
