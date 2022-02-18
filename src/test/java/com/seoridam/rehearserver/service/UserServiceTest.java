package com.seoridam.rehearserver.service;

import com.seoridam.rehearserver.domain.User;
import com.seoridam.rehearserver.dto.JoinRequestDto;
import com.seoridam.rehearserver.fixture.UserFixture.*;
import com.seoridam.rehearserver.global.common.RegistrationPathEnum;
import com.seoridam.rehearserver.repository.UserRepository;
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
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @DisplayName("중복되지 않은 이메일이면 회원가입에 성공한다.")
    @Test
    void join() {
        // given
        final JoinRequestDto dto = JoinRequestDto.builder()
                .name(User1.NAME)
                .email(User1.EMAIL)
                .password(User1.PASSWORD)
                .checkPassword(User1.PASSWORD)
                .nickname(User1.NICKNAME)
                .age(User1.AGE)
                .ventureBefore(User1.VENTURE_BEFORE)
                .job(User1.JOB)
                .businessCategory(User1.BUSINESS_CATEGORY)
                .registrationPath(RegistrationPathEnum.BLOG)
                .build();
        final Optional<User> notFoundUser = Optional.ofNullable(null);

        given(userRepository.findByEmail(any())).willReturn(notFoundUser);
        given(passwordEncoder.encode(dto.getPassword())).willReturn(User1.PASSWORD);

        // when
        userService.join(dto);

        // then
        then(userRepository).should(times(1)).save(any());

    }

    @DisplayName("중복된 이메일이면 에러를 내보낸다.")
    @Test
    void joinWithDuplicateEmail() {
        // given
        final JoinRequestDto dto = JoinRequestDto.builder()
                .name(User1.NAME)
                .email(User1.EMAIL)
                .password(User1.PASSWORD)
                .checkPassword(User1.PASSWORD)
                .nickname(User1.NICKNAME)
                .age(User1.AGE)
                .ventureBefore(User1.VENTURE_BEFORE)
                .job(User1.JOB)
                .businessCategory(User1.BUSINESS_CATEGORY)
                .registrationPath(RegistrationPathEnum.BLOG)
                .build();
        final Optional<User> user = Optional.of(User1.USER);

        given(userRepository.findByEmail(any())).willReturn(user);

        // then
        assertThrows(IllegalArgumentException.class, () -> userService.checkDuplicatedEmail(dto.getEmail()));
        assertThrows(IllegalArgumentException.class, () -> userService.join(dto));
    }

    @DisplayName("중복된 닉네임이면 에러를 내보낸다.")
    @Test
    void joinWithDuplicateNickname() {
        // given
        final JoinRequestDto dto = JoinRequestDto.builder()
                .name(User1.NAME)
                .email(User1.EMAIL)
                .password(User1.PASSWORD)
                .checkPassword(User1.PASSWORD)
                .nickname(User1.NICKNAME)
                .age(User1.AGE)
                .ventureBefore(User1.VENTURE_BEFORE)
                .job(User1.JOB)
                .businessCategory(User1.BUSINESS_CATEGORY)
                .registrationPath(RegistrationPathEnum.BLOG)
                .build();
        final Optional<User> user = Optional.of(User1.USER);

        given(userRepository.findByNickname(any())).willReturn(user);

        // then
        assertThrows(IllegalArgumentException.class, () -> userService.checkDuplicatedNickname(dto.getNickname()));
        assertThrows(IllegalArgumentException.class, () -> userService.join(dto));
    }
}
