package com.seoridam.rehearserver.global.common;

public enum RegistrationPathEnum {

    BLOG("블로그"),
    FRIEND("지인 소개"),
    POTAL("포탈 검색"),
    SNS_ADVERTISEMENT("SNS 광고"),
    YOUTUBE("유튜브"),
    COMMUNITY("온라인 커뮤니티"),
    ETC("기타");


    private final String path;

    RegistrationPathEnum(String path) {
        this.path = path;
    }
}
