package com.seoridam.rehearserver.dto;

import java.time.LocalDate;
import java.util.List;

public interface InterviewProjection {
	Long getId();
	LocalDate getCreateDate();
	Integer getView();
	String getPhotoUrl();
	String getTitle();
	String getIntroText();
	List<TagInfo> getTagList();

	interface TagInfo {
		SubCategoryInfo getSubCategory();

		interface SubCategoryInfo {
			String getName();
		}
	}


}
