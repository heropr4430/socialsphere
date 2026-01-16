package com.socialsphere.backend.controllers;

import com.socialsphere.backend.dtos.PostDTO;
import com.socialsphere.backend.models.Post;
import com.socialsphere.backend.models.User;
import com.socialsphere.backend.repositories.PostRepository;
import com.socialsphere.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO) {

        User user = userRepository.findById(postDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setContent(postDTO.getContent());
        post.setUser(user);

        Post savedPost = postRepository.save(post);

        return ResponseEntity.ok(savedPost);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable Long userId) {

        List<Post> posts = postRepository
                .findByUserIdOrderByCreatedAtDesc(userId);

        return ResponseEntity.ok(posts);
    }
}
