package com.seoridam.rehearserver.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InterviewForm {
	private int view;
	private String video_url;
	private String photo_url;
	private String title;
	private String intro_text;
	private String body_text;
	private String sub_title;

	private List<Long> SubCategoryIdList;
}
