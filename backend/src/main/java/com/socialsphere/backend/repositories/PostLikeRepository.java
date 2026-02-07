package com.socialsphere.backend.repositories;

import com.socialsphere.backend.models.Post;
import com.socialsphere.backend.models.PostLike;
import com.socialsphere.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    boolean existsByUserAndPost(User user, Post post);
    PostLike findByUserAndPost(User user, Post post);
    int  countByPostId(Long postId);

}
