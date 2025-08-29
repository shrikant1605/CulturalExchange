package com.culture.exchange.repository;

import com.culture.exchange.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByPostIdAndUserId(Long postId, Long userId);
    long countByPostId(Long postId);
}

