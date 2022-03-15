package com.seoridam.rehearserver.global.common;

public enum LikeTypeEnum {

    SATISFACTION("만족"),
    NORMAL("보통"),
    REGRET("아쉬움");

    private final String type;

    LikeTypeEnum(String type) {
        this.type = type;
    }
}
