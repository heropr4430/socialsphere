package com.socialsphere.backend.dtos;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long userId;
    private String content;
}
