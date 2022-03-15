package com.seoridam.rehearserver.repository;

import com.seoridam.rehearserver.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
