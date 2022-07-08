package com.seoridam.rehearserver.dto;

import java.time.LocalDate;
import java.util.List;

public interface ArticleSummaryProjection { //아티클 리스트 생성에 필수 정보만 조회
	Long getId();
	LocalDate getCreateDate();
	Integer getView();
	String getWriter();
	String getTitle();
	String getIntroText();
	String getThumbnail();

	List<TagInfo> getTagList();
	interface TagInfo {
		SubCategoryInfo getSubCategory();

		interface SubCategoryInfo {
			String getName();
		}
	}
}
