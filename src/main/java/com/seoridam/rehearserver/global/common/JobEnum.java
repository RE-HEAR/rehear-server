package com.seoridam.rehearserver.global.common;

public enum JobEnum {

    SELF_EMPLOYMENT("자영업"),
    PROFESSIONAL("전문직"),
    STUDENT("학생"),
    MANAGEMENT("경영사무직"),
    MARKETING("마케팅 및 영업"),
    DEVELOPMENT("연구 및 개발"),
    PRODUCTION("생산"),
    ETC("기타");

    private final String job;

    JobEnum(String job) {
        this.job = job;
    }
}
