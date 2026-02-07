package com.socialsphere.backend.repositories;

import com.socialsphere.backend.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
     List<Post> findByAuthor_IdOrderByCreatedAtDesc(Long authorId);

    @Modifying
    @Query("update Post p set p.likeCount = p.likeCount + 1 where p.id = :postId")
    void increaseLikeCount(@Param("postId") Long postId);

    @Modifying
    @Query("update Post p set p.likeCount = p.likeCount - 1 where p.id = :postId and p.likeCount > 0")
    void decreaseLikeCount(@Param("postId") Long postId);
}
