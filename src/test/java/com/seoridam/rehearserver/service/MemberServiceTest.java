package com.seoridam.rehearserver.service;

import com.seoridam.rehearserver.domain.Member;
import com.seoridam.rehearserver.dto.JoinRequestDto;
import com.seoridam.rehearserver.fixture.MemberFixture.*;
import com.seoridam.rehearserver.global.common.RegistrationPathEnum;
import com.seoridam.rehearserver.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @DisplayName("중복되지 않은 이메일이면 회원가입에 성공한다.")
    @Test
    void join() {
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
        final Optional<Member> notFoundUser = Optional.ofNullable(null);

        given(memberRepository.findByEmail(any())).willReturn(notFoundUser);

        // when
        memberService.join(dto);

        // then
        then(memberRepository).should(times(1)).save(any());

    }

    @DisplayName("중복된 이메일이면 에러를 내보낸다.")
    @Test
    void joinWithDuplicateEmail() {
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
        final Optional<Member> member = Optional.of(Member1.MEMBER);

        given(memberRepository.findByEmail(any())).willReturn(member);

        // then
        assertThrows(IllegalArgumentException.class, () -> memberService.checkDuplicatedEmail(dto.getEmail()));
        assertThrows(IllegalArgumentException.class, () -> memberService.join(dto));
    }

    @DisplayName("중복된 닉네임이면 에러를 내보낸다.")
    @Test
    void joinWithDuplicateNickname() {
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
        final Optional<Member> member = Optional.of(Member1.MEMBER);

        given(memberRepository.findByNickname(any())).willReturn(member);

        // then
        assertThrows(IllegalArgumentException.class, () -> memberService.checkDuplicatedNickname(dto.getNickname()));
        assertThrows(IllegalArgumentException.class, () -> memberService.join(dto));
    }
}
