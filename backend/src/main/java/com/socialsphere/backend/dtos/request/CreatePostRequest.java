package com.socialsphere.backend.dtos.request;

import com.socialsphere.backend.dtos.UserDTO;
import com.socialsphere.backend.utils.constant.PostStatus;
import com.socialsphere.backend.utils.constant.PostVisibility;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {
    @NotBlank
    private String content;

    private PostVisibility visibility;

    private PostStatus postStatus;
}
