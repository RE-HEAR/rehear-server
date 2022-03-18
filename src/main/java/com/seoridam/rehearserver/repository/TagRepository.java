package com.seoridam.rehearserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seoridam.rehearserver.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
