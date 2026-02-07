package com.socialsphere.backend.controllers;

import com.socialsphere.backend.dtos.response.UserFollowResponse;
import com.socialsphere.backend.dtos.response.UserResponse;
import com.socialsphere.backend.mapper.FollowMapper;
import com.socialsphere.backend.models.Follow;
import com.socialsphere.backend.models.User;
import com.socialsphere.backend.services.FollowService;
import com.socialsphere.backend.services.UserService;
import com.socialsphere.backend.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follows")
@AllArgsConstructor
public class FollowController {
    private final UserService userService;
    private final FollowService followService;

    @PostMapping("/{userId}")
    public ResponseEntity<UserFollowResponse> followUser(@PathVariable Long userId){
        String username = SecurityUtils.getCurrentUsername();
        User currentUser = userService.getUserByUsername(username);
        Follow follow = followService.follow(currentUser,userId);
        return ResponseEntity.ok(FollowMapper.toResponse(follow));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> unFollowUser(@PathVariable Long userId){
        String username = SecurityUtils.getCurrentUsername();
        User currentUser = userService.getUserByUsername(username);
        followService.unFollow(currentUser,userId);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/followers")
    public ResponseEntity<List<UserResponse>> getFollowers() {
        String username = SecurityUtils.getCurrentUsername();
        User currentUser = userService.getUserByUsername(username);
        List<Follow> followers =  followService.getFollowers(currentUser);
        return ResponseEntity.ok(followers.stream()
                .map(FollowMapper::toFollowerUser)
                .toList());
    }

    @GetMapping("/followings")
    public ResponseEntity<List<UserResponse>> getFollowings() {
        String username = SecurityUtils.getCurrentUsername();
        User currentUser = userService.getUserByUsername(username);
        List<Follow> followings =  followService.getFollowings(currentUser);
        return ResponseEntity.ok(followings.stream()
                .map(FollowMapper::toFollowingUser)
                .toList());
    }
}
