package com.seoridam.rehearserver.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class InterviewListDto {
	private Long id;
	private LocalDate createDate;
	private Integer view;
	private String photoUrl;
	private String title;
	private String introText;
	private List<String> subcategoryNames;

	protected InterviewListDto(){}

}
