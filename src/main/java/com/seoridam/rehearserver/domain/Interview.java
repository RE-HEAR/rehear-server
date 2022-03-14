package com.seoridam.rehearserver.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Interview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INTERVIEW_ID")
	private Long id;

	@Builder.Default
	private LocalDate createDate = LocalDate.now();
	private Integer view;
	private String videoUrl;
	private String photoUrl;
	private String title;
	//소제목
	private String subTitle;
	//클릭 전 소개문
	private String introText;
	//본문
	@Column(columnDefinition = "TEXT")
	private String bodyText;

	//연관관계 매핑 ===================

	@JsonIgnore //무한루프 방지
	@OneToMany(mappedBy = "interview")
	private List<Tag> tagList;

	protected Interview(){}

}
