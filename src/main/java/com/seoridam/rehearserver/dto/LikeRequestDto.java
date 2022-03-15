package com.seoridam.rehearserver.dto;

import com.seoridam.rehearserver.global.common.LikeTypeEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeRequestDto {

    private LikeTypeEnum likeType;
    private Long interviewId;

    @Builder
    public LikeRequestDto(LikeTypeEnum likeType, Long interviewId){
        this.likeType = likeType;
        this.interviewId = interviewId;
    }
}
