package com.seoridam.rehearserver.domain;

import com.seoridam.rehearserver.global.common.LikeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "LIKES")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_ID")
    private Long id;

    //연관관계 매핑 ===================
    @ManyToOne
    @JoinColumn(name = "INTERVIEW_ID")
    private Interview interview;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private LikeTypeEnum likeType;

    protected Like() {}
}
