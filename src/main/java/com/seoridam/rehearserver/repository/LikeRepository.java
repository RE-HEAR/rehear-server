package com.seoridam.rehearserver.repository;

import com.seoridam.rehearserver.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserIdAndInterviewId(long userId, long interviewId);

    @Transactional
    void deleteByUserIdAndInterviewId(long userId, long interviewId);
}
