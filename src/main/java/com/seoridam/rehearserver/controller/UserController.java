package com.seoridam.rehearserver.controller;

import com.seoridam.rehearserver.dto.JoinRequestDto;
import com.seoridam.rehearserver.dto.LoginRequestDto;
import com.seoridam.rehearserver.global.common.SuccessResponse;
import com.seoridam.rehearserver.service.LoginService;
import com.seoridam.rehearserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import static com.seoridam.rehearserver.global.common.StatusEnum.OK;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/join")
    public void join(@Valid @RequestBody final JoinRequestDto joinRequestDto) {
        userService.join(joinRequestDto);
    }

    @GetMapping("/duplicated/email/{email}")
    public void isDuplicatedEmail(@PathVariable String email) {
        userService.checkDuplicatedEmail(email);
    }

    @GetMapping("duplicated/nickname/{nickname}")
    public void isDuplicatedNickname(@PathVariable String nickname) {
        userService.checkDuplicatedNickname(nickname);
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
