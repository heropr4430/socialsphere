package com.socialsphere.backend.services;

import com.socialsphere.backend.dtos.response.UserFollowResponse;
import com.socialsphere.backend.models.Follow;
import com.socialsphere.backend.models.User;
import com.socialsphere.backend.repositories.FollowRepository;
import com.socialsphere.backend.repositories.UserRepository;
import com.socialsphere.backend.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private  final UserService userService;
    public Follow follow(User currentUser,Long followingUserId){

        User followingUser = userRepository.findById(followingUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (currentUser == followingUser){
            throw new RuntimeException("You can't follows yourself");
        }

        if (followRepository.existsByFollowerAndFollowing(currentUser, followingUser)) {
            throw new RuntimeException("You already followed this user");
        }

        Follow newFollow = new Follow();
        newFollow.setFollower(currentUser);
        newFollow.setFollowing(followingUser);
        return followRepository.save(newFollow);
    }

    @Transactional
    public void unFollow(User currentUser,Long followingUserId){

        User followingUser = userRepository.findById(followingUserId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        if (!followRepository.existsByFollowerAndFollowing(currentUser, followingUser)) {
            throw new RuntimeException("You still haven't followed this user.");
        }
        followRepository.deleteByFollowerAndFollowing(currentUser,followingUser);
    }

    public List<Follow> getFollowers(User currentUser){
        List<Follow> followrs = followRepository.findByFollowing(currentUser);
        return followrs;
    }

    public List<Follow> getFollowings(User currentUser){
        List<Follow> followings = followRepository.findByFollower(currentUser);
        return followings;
    }
}
