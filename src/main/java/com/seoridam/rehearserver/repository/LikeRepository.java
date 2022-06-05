package com.seoridam.rehearserver.repository;

import com.seoridam.rehearserver.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserIdAndArticleId(long userId, long articleId);

    @Transactional
    void deleteByUserIdAndArticleId(long userId, long articleId);
}
