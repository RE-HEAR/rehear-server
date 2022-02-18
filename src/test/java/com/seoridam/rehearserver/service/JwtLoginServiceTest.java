package com.seoridam.rehearserver.service;

import com.seoridam.rehearserver.domain.User;
import com.seoridam.rehearserver.dto.LoginRequestDto;
import com.seoridam.rehearserver.fixture.UserFixture.*;
import com.seoridam.rehearserver.global.util.JwtTokenProvider;
import com.seoridam.rehearserver.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class JwtLoginServiceTest {

    @InjectMocks
    private JwtLoginService jwtLoginService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    @DisplayName("이메일이 존재하고 비밀번호와 일치한다면 로그인에 성공한다.")
    @Test
    void login() {
        // given
        final LoginRequestDto loginDto = LoginRequestDto.builder()
                .email(User1.EMAIL)
                .password(User1.PASSWORD)
                .build();
        final Optional<User> user = Optional.of(User1.USER);

        given(userRepository.findByEmail(loginDto.getEmail())).willReturn(user);
        given(passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword())).willReturn(true);

        // when
        jwtLoginService.login(loginDto);

        // then
        then(jwtTokenProvider).should(times(1)).createToken(any(), any());

    }

}
