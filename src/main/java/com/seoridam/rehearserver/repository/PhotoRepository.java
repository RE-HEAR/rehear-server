package com.seoridam.rehearserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seoridam.rehearserver.domain.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}