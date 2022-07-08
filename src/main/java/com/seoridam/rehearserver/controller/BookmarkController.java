package com.seoridam.rehearserver.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seoridam.rehearserver.domain.User;
import com.seoridam.rehearserver.dto.ArticleSummaryProjection;
import com.seoridam.rehearserver.service.BookmarkService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookmarkController {

	public final BookmarkService bookmarkService;

	// 북마크 추가 혹은 제거
	@PostMapping("/users/bookmark/{articleId}")
	public void postLike(@PathVariable Long articleId, @AuthenticationPrincipal User user){
		bookmarkService.postBookmark(articleId, user);
	}

	// 유저가 한 북마크 전체 조회
	@GetMapping("/users/bookmark")
	public List<ArticleSummaryProjection> getBookmark(@AuthenticationPrincipal User user){
		return bookmarkService.getArticleListByUserId(user.getId());
	}


}
