package com.seoridam.rehearserver.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ArticleForm {
	private Integer view;
	private String title;
	private String bodyText;

	private List<Long> SubCategoryIdList;
	private List<String> PhotoPathList;
}
