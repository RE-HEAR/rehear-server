package com.seoridam.rehearserver.controller;

import com.seoridam.rehearserver.dto.JoinRequestDto;
import com.seoridam.rehearserver.dto.LoginRequestDto;
import com.seoridam.rehearserver.global.common.StatusEnum;
import com.seoridam.rehearserver.global.common.SuccessResponse;
import com.seoridam.rehearserver.service.LoginService;
import com.seoridam.rehearserver.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import static com.seoridam.rehearserver.global.common.StatusEnum.CREATED;
import static com.seoridam.rehearserver.global.common.StatusEnum.OK;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final LoginService loginService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/join")
    public StatusEnum join(@Valid @RequestBody final JoinRequestDto joinRequestDto) {
        memberService.join(joinRequestDto);
        return CREATED;
    }

    @GetMapping("/duplicated/email/{email}")
    public StatusEnum isDuplicatedEmail(@PathVariable String email) {
        memberService.checkDuplicatedEmail(email);
        return OK;
    }

    @GetMapping("duplicated/nickname/{nickname}")
    public StatusEnum isDuplicatedNickname(@PathVariable String nickname) {
        memberService.checkDuplicatedNickname(nickname);
        return OK;
    }

    @PostMapping("/login")
    public SuccessResponse login(@Valid @RequestBody final LoginRequestDto loginRequestDto) {
        String token = loginService.login(loginRequestDto);
        return SuccessResponse.builder()
                .status(OK)
                .message("로그인 성공")
                .data(token)
                .build();
    }
}
