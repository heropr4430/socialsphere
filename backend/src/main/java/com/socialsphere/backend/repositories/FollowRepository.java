package com.socialsphere.backend.repositories;

import com.socialsphere.backend.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
