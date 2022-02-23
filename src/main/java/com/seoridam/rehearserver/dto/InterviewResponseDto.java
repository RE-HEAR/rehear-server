package com.seoridam.rehearserver.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class InterviewResponseDto {
	private Long id;
	private LocalDate createDate;
	private Integer view;
	private String video_url;
	private String photo_url;
	private String title;
	private String intro_text;
	private String body_text;
	private String sub_title;
	private List<String> subcategory_names;

	protected InterviewResponseDto(){}

}
