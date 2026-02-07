package com.socialsphere.backend.services;

import com.socialsphere.backend.dtos.request.CreatePostRequest;
import com.socialsphere.backend.dtos.response.CommentResponse;
import com.socialsphere.backend.dtos.response.PostDetailResponse;
import com.socialsphere.backend.mapper.AuthorMapper;
import com.socialsphere.backend.models.Comment;
import com.socialsphere.backend.models.Post;
import com.socialsphere.backend.models.User;
import com.socialsphere.backend.repositories.CommentRepository;
import com.socialsphere.backend.repositories.PostLikeRepository;
import com.socialsphere.backend.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;

    public Post createPost(CreatePostRequest request, User curUser){
        Post post = new Post();
        post.setAuthor(curUser);
        post.setContent(request.getContent());
        post.setLikeCount(0);
        post.setCommentCount(0);
        post.setShareCount(0);
        post.setVisibility(request.getVisibility());
        post.setStatus(request.getPostStatus());
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public List<Post> getPostsByUserId(Long userId){
        return postRepository.findByAuthor_IdOrderByCreatedAtDesc(userId);
    }

    public PostDetailResponse getPostDetail(Long postId, User curUser){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        boolean isLiked = postLikeRepository.existsByUserAndPost(curUser, post);

        List<CommentResponse> comments = commentRepository
                .findByPostOrderByCreatedAtAsc(post)
                .stream()
                .map(this::mapToCommentResponse)
                .toList();

        return PostDetailResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .author(AuthorMapper.toResponse(post.getAuthor()))
                .likeCount(post.getLikeCount())
                .commentCount(post.getCommentCount())
                .likedByCurrentUser(isLiked)
                .comments(comments)
                .build();
    }

    private CommentResponse mapToCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(AuthorMapper.toResponse(comment.getUser()))
                .createdAt(comment.getCreatedAt())
                .build();
    }







}
