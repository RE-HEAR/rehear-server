package com.seoridam.rehearserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seoridam.rehearserver.domain.LikeByDate;

public interface LikeByDateRepository extends JpaRepository<LikeByDate, Long> {

}
