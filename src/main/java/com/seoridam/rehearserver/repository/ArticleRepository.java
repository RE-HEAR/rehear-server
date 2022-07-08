package com.seoridam.rehearserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.seoridam.rehearserver.domain.Article;
import com.seoridam.rehearserver.dto.ArticleProjection;
import com.seoridam.rehearserver.dto.ArticleSummaryProjection;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	//Projection 기능:
	//find 와 by는 무조건 써줘야 함
	//'org.springframework.boot' version '2.6.2' 이하로만 작동

	//article 조회
	ArticleProjection findArticleProjectionById(Long id);

	//article summary 조회 byId
	ArticleSummaryProjection findArticleSummaryProjectionById(Long id);

	//article 리스트 페이징 조회
	Page<ArticleSummaryProjection> findArticleSummaryProjectionsBy(Pageable pageable);

}