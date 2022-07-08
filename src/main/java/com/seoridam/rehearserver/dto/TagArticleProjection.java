package com.seoridam.rehearserver.dto;

import java.time.LocalDate;

public interface TagArticleProjection {
	Long getId();
	Long getArticleId();
	LocalDate getArticleCreateDate();
	Integer getArticleView();
	String getArticleThumbnail();
	String getArticleTitle();
	String getArticleIntroText();
	String getArticleWriter();
	String getSubCategoryName();
}
