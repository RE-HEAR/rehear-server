package com.seoridam.rehearserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.seoridam.rehearserver.domain.Tag;
import com.seoridam.rehearserver.dto.TagArticleProjection;

public interface TagRepository extends JpaRepository<Tag, Long> {

	//인터뷰 ID를 타고들어가 인터뷰까지 한꺼번에 조회해옴
	Page<TagArticleProjection> findArticleListBySubCategoryId(@Param("subcategoryId") Long id, Pageable pageable);

}
