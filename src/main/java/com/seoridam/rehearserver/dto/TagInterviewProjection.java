package com.seoridam.rehearserver.dto;

import java.time.LocalDate;

public interface TagInterviewProjection {
	Long getId();
	Long getInterviewId();
	LocalDate getInterviewCreateDate();
	Integer getInterviewView();
	String getInterviewPhotoUrl();
	String getInterviewTitle();
	String getInterviewIntroText();
	String getSubCategoryName();
}
