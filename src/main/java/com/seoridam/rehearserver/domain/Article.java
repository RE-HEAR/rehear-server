package com.seoridam.rehearserver.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTICLE_ID")
	private Long id;

	@Builder.Default
	private LocalDate createDate = LocalDate.now();
	private Integer view;
	private String title;
	// 본문
	@Column(columnDefinition = "TEXT")
	private String bodyText;

	// 연관관계 매핑 ===================

	@JsonIgnore // 무한루프 방지
	@OneToMany(mappedBy = "article")
	private List<Tag> tagList;

	@JsonIgnore // 무한루프 방지
	@OneToMany(mappedBy = "article")
	private List<Photo> photoList;

	protected Article() {
	}

}
