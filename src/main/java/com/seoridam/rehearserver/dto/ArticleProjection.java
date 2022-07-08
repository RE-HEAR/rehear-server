package com.seoridam.rehearserver.dto;

import java.time.LocalDate;
import java.util.List;


public interface ArticleProjection {
	//Article 전체 내용 조회 + photo List + Tag List

	Long getId();
	LocalDate getCreateDate();
	Integer getView();
	String getWriter();
	String getTitle();
	String getSubTitle();

	String getIntroText();
	String getBodyText();
	String getThumbnail();

	List<TagInfo> getTagList();
	interface TagInfo {
		ArticleProjection.TagInfo.SubCategoryInfo getSubCategory();

		interface SubCategoryInfo {
			String getName();
		}
	}
	List<PhotoInfo> getPhotoList();
	interface PhotoInfo {
		String getPath();
	}
}