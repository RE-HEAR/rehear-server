package com.seoridam.rehearserver.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ArticleResponseDto {
	private LocalDate createDate;
	private Integer view;
	private String photoUrl;
	private String title;
	private String introText;
	private String bodyText;
	private String subTitle;
	private List<String> subcategoryNames;

	protected ArticleResponseDto(){}

}
