package com.seoridam.rehearserver.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.seoridam.rehearserver.domain.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

	//인터뷰 조회
	@Override
	Optional<Interview> findById(Long id);

	// 인터뷰 목록 페이지별 조회
	// 추후 성능 개선 필요
	@Override
	Page<Interview> findAll(Pageable pageable);

}