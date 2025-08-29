package com.culture.exchange.repository;

import com.culture.exchange.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

