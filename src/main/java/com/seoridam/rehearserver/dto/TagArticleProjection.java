package com.seoridam.rehearserver.dto;

import java.time.LocalDate;

public interface TagArticleProjection {
	Long getId();

	Long getArticleId();

	LocalDate getArticleCreateDate();

	Integer getArticleView();

	String getArticlePhotoUrl();

	String getArticleTitle();

	String getSubCategoryName();
}
