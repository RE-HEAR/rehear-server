package com.seoridam.rehearserver.dto;

import java.time.LocalDate;
import java.util.List;

public interface ArticleProjection {
	Long getId();

	LocalDate getCreateDate();

	Integer getView();

	String getTitle();

	List<TagInfo> getTagList();

	interface TagInfo {
		SubCategoryInfo getSubCategory();

		interface SubCategoryInfo {
			String getName();
		}
	}

	List<PhotoInfo> getPhotoList();

	interface PhotoInfo {
		String getPath();
	}

}
