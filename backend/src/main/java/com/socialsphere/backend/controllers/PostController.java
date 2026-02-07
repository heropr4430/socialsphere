package com.socialsphere.backend.controllers;

import com.socialsphere.backend.dtos.request.CreatePostRequest;
import com.socialsphere.backend.dtos.response.PostDetailResponse;
import com.socialsphere.backend.dtos.response.PostResponse;
import com.socialsphere.backend.mapper.FollowMapper;
import com.socialsphere.backend.mapper.PostMapper;
import com.socialsphere.backend.models.Post;
import com.socialsphere.backend.models.User;
import com.socialsphere.backend.repositories.PostRepository;
import com.socialsphere.backend.repositories.UserRepository;
import com.socialsphere.backend.security.CurrentUserService;
import com.socialsphere.backend.services.PostLikeService;
import com.socialsphere.backend.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {

    private final CurrentUserService currentUserService;
    private final PostService postService;
    private final PostLikeService postLikeService;


    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody CreatePostRequest createPostRequest) {

        User curUser = currentUserService.getCurrentUser();

        Post savedPost = postService.createPost(createPostRequest, curUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PostMapper.toResponse(savedPost));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponse>> getPostsByUser(@PathVariable Long userId) {

        List<Post> posts = postService.getPostsByUserId(userId);

        return ResponseEntity.ok(posts.stream()
                .map(PostMapper::toResponse)
                .toList());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable Long postId) {
        User curUser = currentUserService.getCurrentUser();

        PostDetailResponse postDetail =
                postService.getPostDetail(postId,curUser);
        return ResponseEntity.ok(postDetail);
    }

    @GetMapping("/me")
    public ResponseEntity<List<PostResponse>> getMyPosts() {

        User curUser = currentUserService.getCurrentUser();
        List<Post> posts = postService.getPostsByUserId(curUser.getId());

        return ResponseEntity.ok(posts.stream()
                .map(PostMapper::toResponse)
                .toList());
    }

    @PostMapping("/{postId}/like")
    public String likePost(@PathVariable Long postId){
        User curUser = currentUserService.getCurrentUser();
        boolean isLikePost = postLikeService.isLikePost(postId,curUser);
        if(isLikePost){
            return  postLikeService.unlikePost(postId,curUser);
        }
        else {
            return postLikeService.likePost(postId,curUser);
        }
    }
}
