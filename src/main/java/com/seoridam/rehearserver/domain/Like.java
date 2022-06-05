package com.seoridam.rehearserver.domain;

import com.seoridam.rehearserver.global.common.LikeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "LIKES")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_ID")
    private Long id;

    @Builder.Default
    private LocalDateTime createTime = LocalDateTime.now();

    //연관관계 매핑 ===================
    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Enumerated(EnumType.STRING)
    private LikeTypeEnum likeType;

    protected Like() {}
}
