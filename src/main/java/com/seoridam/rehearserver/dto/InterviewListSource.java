package com.seoridam.rehearserver.dto;

import java.time.LocalDate;
import java.util.List;

import com.seoridam.rehearserver.domain.SubCategory;

public interface InterviewListSource {
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
