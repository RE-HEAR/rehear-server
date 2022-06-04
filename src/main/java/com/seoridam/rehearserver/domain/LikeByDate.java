package com.seoridam.rehearserver.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class LikeByDate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LIKE_BY_DATE_ID")
	private Long id;

	private Date date;
	private Integer count;

	//연관관계 매핑 ===================

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ARTICLE_ID")
	private Article article;

	protected LikeByDate() {}

	public void setArticle(Article article) {
		this.article = article;
	}
}
