package com.seoridam.rehearserver.dto;

import com.seoridam.rehearserver.global.common.JobEnum;
import com.seoridam.rehearserver.global.common.RegistrationPathEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JoinRequestDto {

    private String email;
    private String password;
    private String checkPassword;
    private String nickname;
    private String name;
    private int age;
    private boolean ventureBefore;
    private JobEnum job;
    private String businessCategory;
    private RegistrationPathEnum registrationPath;


    @Builder
    public JoinRequestDto(String email, String password, String checkPassword, String nickname, String name, int age, boolean ventureBefore, JobEnum job, String businessCategory, RegistrationPathEnum registrationPath){
        this.email = email;
        this.password = password;
        this.checkPassword = checkPassword;
        this.nickname = nickname;
        this.name = name;
        this.age = age;
        this.ventureBefore = ventureBefore;
        this.job = job;
        this.businessCategory = businessCategory;
        this.registrationPath = registrationPath;
    }
}
