package com.socialsphere.backend.mapper;

import com.socialsphere.backend.dtos.response.UserFollowResponse;
import com.socialsphere.backend.dtos.response.UserResponse;
import com.socialsphere.backend.models.Follow;
import com.socialsphere.backend.models.User;

public class FollowMapper {

    // Dùng cho API /followers
    public static UserResponse toFollowerUser(Follow follow) {
        return UserMapper.toResponse(follow.getFollower());
    }

    // Dùng cho API /followings
    public static UserResponse toFollowingUser(Follow follow) {
        return UserMapper.toResponse(follow.getFollowing());
    }
    public static UserFollowResponse toResponse(Follow follow) {
        return new UserFollowResponse(
                follow.getId(),
                UserMapper.toResponse(follow.getFollower()),
                UserMapper.toResponse(follow.getFollowing())
        );
    }
}
