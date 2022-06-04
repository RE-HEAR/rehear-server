package com.seoridam.rehearserver.dto;

import com.seoridam.rehearserver.global.common.LikeTypeEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeRequestDto {

    private LikeTypeEnum likeType;
    private Long articleId;

    @Builder
    public LikeRequestDto(LikeTypeEnum likeType, Long articleId){
        this.likeType = likeType;
        this.articleId = articleId;
    }
}