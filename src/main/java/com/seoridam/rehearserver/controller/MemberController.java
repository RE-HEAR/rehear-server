package com.seoridam.rehearserver.controller;

import com.seoridam.rehearserver.dto.JoinRequestDto;
import com.seoridam.rehearserver.dto.LoginRequestDto;
import com.seoridam.rehearserver.global.common.StatusEnum;
import com.seoridam.rehearserver.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import static com.seoridam.rehearserver.global.common.StatusEnum.OK;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/join")
    public StatusEnum signUp(@Valid @RequestBody final JoinRequestDto joinRequestDto) {
        memberService.join(joinRequestDto);
        return OK;
    }

    @GetMapping("/duplicated/email/{email}")
    public StatusEnum isDuplicatedEmail(@PathVariable String email) {
        memberService.isDuplicatedEmail(email);
        return OK;
    }

    @GetMapping("duplicated/nickname/{nickname}")
    public StatusEnum isDuplicatedNickname(@PathVariable String nickname) {
        memberService.isDuplicatedNickname(nickname);
        return OK;
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody final LoginRequestDto loginRequestDto) {
        return memberService.login(loginRequestDto);
    }
}
