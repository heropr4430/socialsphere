package com.socialsphere.backend.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class UserFollowResponse {
    private Long id;
    private UserResponse follower;
    private UserResponse following;

}
