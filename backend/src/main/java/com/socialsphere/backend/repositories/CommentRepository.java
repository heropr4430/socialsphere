package com.socialsphere.backend.repositories;

import com.socialsphere.backend.models.Comment;
import com.socialsphere.backend.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostOrderByCreatedAtAsc(Post post);
}
