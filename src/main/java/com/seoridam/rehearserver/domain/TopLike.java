package com.seoridam.rehearserver.domain;

import java.time.LocalDateTime;

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
public class TopLike {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TOPLIKE_ID")
	private Long id;

	private Integer total;
	private Integer likeToday;
	private Integer likePastday;
	// like_week = total - like_pastday + like_today 로 갱신
	private Integer likeWeek;

	//연관관계 매핑 ===================

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INTERVIEW_ID")
	private Interview interview;

	protected TopLike() {}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}
}
