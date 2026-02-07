package com.socialsphere.backend.models;

import com.socialsphere.backend.utils.constant.PostStatus;
import com.socialsphere.backend.utils.constant.PostVisibility;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private int likeCount;
    private int commentCount;
    private int shareCount;

    @Enumerated(EnumType.STRING)
    private PostVisibility visibility;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


