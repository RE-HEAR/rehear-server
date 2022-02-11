package com.seoridam.rehearserver.service;

import com.seoridam.rehearserver.domain.Member;
import com.seoridam.rehearserver.dto.JoinRequestDto;
import com.seoridam.rehearserver.dto.LoginRequestDto;
import com.seoridam.rehearserver.global.util.JwtTokenProvider;
import com.seoridam.rehearserver.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void join(JoinRequestDto dto){
        if(!dto.getPassword().equals(dto.getCheckPassword()))
            throw new IllegalArgumentException("비밀번호 확인이 되지 않았습니다.");
        Member member = Member.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .nickname(dto.getNickname())
                .name(dto.getName())
                .age(dto.getAge())
                .ventureBefore(dto.isVentureBefore())
                .job(dto.getJob())
                .businessCategory(dto.getBusinessCategory())
                .roles(Collections.singletonList("ROLE_MEMBER"))
                .build();
        memberRepository.save(member);
    }

    public void isDuplicatedEmail(String email){
        boolean isDuplicated = memberRepository.findByEmail(email).isPresent();
        if (isDuplicated) {
            throw new IllegalArgumentException("이미 등록된 메일입니다.");
        }
    }

    public void isDuplicatedNickname(String nickname){
        boolean isDuplicated = memberRepository.findByNickname(nickname).isPresent();

        if (isDuplicated) {
            throw new IllegalArgumentException("이미 사용중인 닉네임입니다.");
        }

    }

    public String login(LoginRequestDto loginRequestDto){
        Member member = memberRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 계정입니다."));
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }
}
