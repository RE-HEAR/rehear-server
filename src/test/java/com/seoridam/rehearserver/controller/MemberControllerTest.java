package com.seoridam.rehearserver.controller;

import com.seoridam.rehearserver.dto.JoinRequestDto;
import com.seoridam.rehearserver.dto.LoginRequestDto;
import com.seoridam.rehearserver.fixture.MemberFixture.*;
import com.seoridam.rehearserver.global.common.RegistrationPathEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemberControllerTest extends ControllerTest {

    @DisplayName("이메일과 닉네임이 중복된 회원이 아니라면 회원 가입에 성공한다.")
    @Test
    void join() throws Exception {
        // given
        final JoinRequestDto dto = JoinRequestDto.builder()
                .name(Member1.NAME)
                .email(Member1.EMAIL)
                .password(Member1.PASSWORD)
                .checkPassword(Member1.PASSWORD)
                .nickname(Member1.NICKNAME)
                .age(Member1.AGE)
                .ventureBefore(Member1.VENTURE_BEFORE)
                .job(Member1.JOB)
                .businessCategory(Member1.BUSINESS_CATEGORY)
                .registrationPath(RegistrationPathEnum.BLOG)
                .build();

        // when
        final ResultActions actions = mvc.perform(post("/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                        .andDo(print());

        // then
        actions
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @DisplayName("이메일이 중복된 사용자는 회원가입에 실패한다.")
    @Test
    void JoinWithDuplicateEmail() throws Exception {
        // given
        final JoinRequestDto dto1 = JoinRequestDto.builder()
                .name(Member1.NAME)
                .email(Member1.EMAIL)
                .password(Member1.PASSWORD)
                .checkPassword(Member1.PASSWORD)
                .nickname(Member1.NICKNAME)
                .age(Member1.AGE)
                .ventureBefore(Member1.VENTURE_BEFORE)
                .job(Member1.JOB)
                .businessCategory(Member1.BUSINESS_CATEGORY)
                .registrationPath(RegistrationPathEnum.BLOG)
                .build();

        final JoinRequestDto dto2 = JoinRequestDto.builder()
                .name(Member2.NAME)
                .email(Member1.EMAIL)
                .password(Member2.PASSWORD)
                .checkPassword(Member2.PASSWORD)
                .nickname(Member2.NICKNAME)
                .age(Member2.AGE)
                .ventureBefore(Member2.VENTURE_BEFORE)
                .job(Member2.JOB)
                .businessCategory(Member2.BUSINESS_CATEGORY)
                .registrationPath(RegistrationPathEnum.BLOG)
                .build();

        // when
        mvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto1)));

        final ResultActions actions = mvc.perform(post("/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto2)))
                        .andDo(print());

        // then
        actions
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @DisplayName("닉네임이 중복된 사용자는 회원가입에 실패한다.")
    @Test
    void JoinWithDuplicateNickname() throws Exception {
        // given
        final JoinRequestDto dto1 = JoinRequestDto.builder()
                .name(Member1.NAME)
                .email(Member1.EMAIL)
                .password(Member1.PASSWORD)
                .checkPassword(Member1.PASSWORD)
                .nickname(Member1.NICKNAME)
                .age(Member1.AGE)
                .ventureBefore(Member1.VENTURE_BEFORE)
                .job(Member1.JOB)
                .businessCategory(Member1.BUSINESS_CATEGORY)
                .registrationPath(RegistrationPathEnum.BLOG)
                .build();

        final JoinRequestDto dto2 = JoinRequestDto.builder()
                .name(Member2.NAME)
                .email(Member2.EMAIL)
                .password(Member2.PASSWORD)
                .checkPassword(Member2.PASSWORD)
                .nickname(Member1.NICKNAME)
                .age(Member2.AGE)
                .ventureBefore(Member2.VENTURE_BEFORE)
                .job(Member2.JOB)
                .businessCategory(Member2.BUSINESS_CATEGORY)
                .registrationPath(RegistrationPathEnum.BLOG)
                .build();

        // when
        mvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto1)));

        final ResultActions actions = mvc.perform(post("/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto2)))
                .andDo(print());

        // then
        actions
                .andExpect(status().isBadRequest())
                .andDo(print());
    }


    @DisplayName("회원의 이메일이 존재하고 패스워드가 일치하면 로그인에 성공한다.")
    @Test
    void login() throws Exception {
        // given
        final JoinRequestDto joinDto = JoinRequestDto.builder()
                .name(Member1.NAME)
                .email(Member1.EMAIL)
                .password(Member1.PASSWORD)
                .checkPassword(Member1.PASSWORD)
                .nickname(Member1.NICKNAME)
                .age(Member1.AGE)
                .ventureBefore(Member1.VENTURE_BEFORE)
                .job(Member1.JOB)
                .businessCategory(Member1.BUSINESS_CATEGORY)
                .registrationPath(RegistrationPathEnum.BLOG)
                .build();

        final LoginRequestDto loginDto = LoginRequestDto.builder()
                .email(Member1.EMAIL)
                .password(Member1.PASSWORD)
                .build();

        // when
        mvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinDto)));

        final ResultActions actions = mvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                        .andDo(print());

        // then
        actions
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("이메일이 존재하지 않은 사용자는 로그인에 실패한다.")
    @Test
    void loginWithNotFoundEmail() throws Exception {
        // given
        final LoginRequestDto dto = LoginRequestDto.builder()
                .email(Member2.EMAIL)
                .password(Member2.PASSWORD)
                .build();

        // when
        final ResultActions actions = mvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                        .andDo(print());

        // then
        actions
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @DisplayName("이메일이 존재하지만 패스워드가 일치하지 않은 사용자는 로그인에 실패한다.")
    @Test
    void loginWithInvalidPassword() throws Exception {
        // given
        final JoinRequestDto joinDto = JoinRequestDto.builder()
                .name(Member1.NAME)
                .email(Member1.EMAIL)
                .password(Member1.PASSWORD)
                .checkPassword(Member1.PASSWORD)
                .nickname(Member1.NICKNAME)
                .age(Member1.AGE)
                .ventureBefore(Member1.VENTURE_BEFORE)
                .job(Member1.JOB)
                .businessCategory(Member1.BUSINESS_CATEGORY)
                .registrationPath(RegistrationPathEnum.BLOG)
                .build();

        final LoginRequestDto loginDto = LoginRequestDto.builder()
                .email(Member1.EMAIL)
                .password(Member2.PASSWORD)
                .build();

        // when
        mvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinDto)));

        final ResultActions actions = mvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                        .andDo(print());

        // then
        actions
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @DisplayName("이미 등록된 이메일이라면 중복확인에 실패한다.")
    @Test
    void CheckDuplicatedEmail() throws Exception {
        // given
        final JoinRequestDto joinDto = JoinRequestDto.builder()
                .name(Member1.NAME)
                .email(Member1.EMAIL)
                .password(Member1.PASSWORD)
                .checkPassword(Member1.PASSWORD)
                .nickname(Member1.NICKNAME)
                .age(Member1.AGE)
                .ventureBefore(Member1.VENTURE_BEFORE)
                .job(Member1.JOB)
                .businessCategory(Member1.BUSINESS_CATEGORY)
                .registrationPath(RegistrationPathEnum.BLOG)
                .build();

        // when
        mvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinDto)));

        final ResultActions actions = mvc.perform(get("/duplicated/email/{email}", Member1.EMAIL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // then
        actions
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @DisplayName("이미 등록된 닉네임이라면 중복확인에 실패한다.")
    @Test
    void CheckDuplicatedNickname() throws Exception {
        // given
        final JoinRequestDto joinDto = JoinRequestDto.builder()
                .name(Member1.NAME)
                .email(Member1.EMAIL)
                .password(Member1.PASSWORD)
                .checkPassword(Member1.PASSWORD)
                .nickname(Member1.NICKNAME)
                .age(Member1.AGE)
                .ventureBefore(Member1.VENTURE_BEFORE)
                .job(Member1.JOB)
                .businessCategory(Member1.BUSINESS_CATEGORY)
                .registrationPath(RegistrationPathEnum.BLOG)
                .build();

        // when
        mvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinDto)));

        final ResultActions actions = mvc.perform(get("/duplicated/nickname/{nickname}", Member1.NICKNAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // then
        actions
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}
