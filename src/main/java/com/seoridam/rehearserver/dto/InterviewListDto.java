package com.seoridam.rehearserver.dto;

import java.time.LocalDate;
import java.util.List;

import com.seoridam.rehearserver.domain.Tag;

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
	private String photo_url;
	private String title;
	private String intro_text;
	private List<String> subcategory_names;

	protected InterviewListDto(){}

}
