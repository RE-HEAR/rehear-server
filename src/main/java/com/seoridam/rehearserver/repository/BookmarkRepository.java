package com.seoridam.rehearserver.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seoridam.rehearserver.domain.Bookmark;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	Optional<Bookmark> findByUserIdAndArticleId(long userId, long articleId);

	@Transactional
	void deleteBookmarkByUserIdAndArticleId(long userId, long articleId);

	List<Bookmark> findAllByUserId(Long userId);
}
