package com.seoridam.rehearserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seoridam.rehearserver.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
