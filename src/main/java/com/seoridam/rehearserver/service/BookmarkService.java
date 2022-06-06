package com.seoridam.rehearserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.seoridam.rehearserver.domain.Bookmark;
import com.seoridam.rehearserver.domain.User;
import com.seoridam.rehearserver.dto.ArticleProjection;
import com.seoridam.rehearserver.repository.ArticleRepository;
import com.seoridam.rehearserver.repository.BookmarkRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookmarkService {
	private final BookmarkRepository bookmarkRepository;
	private final ArticleRepository articleRepository;

	// 유저가 한 북마크 게시글 리스트 전체 조회
	public List<ArticleProjection> getArticleListByUserId(Long userId){
		List<ArticleProjection> articleProjections = new ArrayList<>();
		List<Bookmark> bookmarkList = bookmarkRepository.findAllByUserId(userId);
		for (Bookmark bookmark: bookmarkList) {
			ArticleProjection projection = articleRepository.findArticleProjectionById(bookmark.getArticle().getId());
			articleProjections.add(projection);
		}
		return articleProjections;
	}

	// 북마크 생성
	public void postBookmark(Long articleId, User user) {
		// 북마크 되어있는지 조회
		Optional<Bookmark> found = bookmarkRepository.findByUserIdAndArticleId(user.getId(), articleId);
		if(found.isPresent()){
			bookmarkRepository.delete(found.get());
		} else{
			Bookmark bookmark = Bookmark.builder().article(articleRepository.findById(articleId).get()).user(user).build();
			bookmarkRepository.save(bookmark);
		}
	}

}
