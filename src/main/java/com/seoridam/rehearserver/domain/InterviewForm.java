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
	private String videoUrl;
	private String photoUrl;
	private String title;
	private String introText;
	private String bodyText;
	private String subTitle;

	private List<Long> SubCategoryIdList;
}
