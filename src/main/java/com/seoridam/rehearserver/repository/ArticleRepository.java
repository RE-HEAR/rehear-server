package com.seoridam.rehearserver.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.seoridam.rehearserver.domain.Article;
import com.seoridam.rehearserver.dto.ArticleProjection;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	//인터뷰 조회
	@Override
	Optional<Article> findById(Long id);

	//find 와 by는 무조건 써줘야 함
	//id 'org.springframework.boot' version '2.6.2' 이하로만 작동
	Page<ArticleProjection> findArticleProjectionsBy(Pageable pageable);

}