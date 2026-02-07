package com.socialsphere.backend.services;

import com.socialsphere.backend.models.Post;
import com.socialsphere.backend.models.PostLike;
import com.socialsphere.backend.models.User;
import com.socialsphere.backend.repositories.PostLikeRepository;
import com.socialsphere.backend.repositories.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    public boolean isLikePost(Long postId, User curUser){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return postLikeRepository.existsByUserAndPost(curUser,post);
    }
    @Transactional
    public String likePost(Long postId, User curUser){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        PostLike postLike = new PostLike();
        postLike.setUser(curUser);
        postLike.setPost(post);

        postLikeRepository.save(postLike);

        postRepository.increaseLikeCount(post.getId());
        return "liked";
    }

    @Transactional
    public String unlikePost(Long postId, User curUser){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        PostLike postLike = postLikeRepository.findByUserAndPost(curUser,post);

        postLikeRepository.delete(postLike);
        postRepository.decreaseLikeCount(post.getId());
        return "unliked";
    }
}
