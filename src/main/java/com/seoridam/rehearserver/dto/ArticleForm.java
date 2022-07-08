package com.seoridam.rehearserver.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ArticleForm { //Article 생성 form
	private Integer view;
	private String writer;
	private String title;
	private String subTitle;
	private String introText;
	private String bodyText;
	private String thumbnail;

	private List<Long> SubCategoryIdList;
	private List<String> PhotoPathList;
}