package com.seoridam.rehearserver.controller;

import com.seoridam.rehearserver.domain.User;
import com.seoridam.rehearserver.dto.LikeRequestDto;
import com.seoridam.rehearserver.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    
    @PostMapping("members/like")
    public void postLike(@Valid @RequestBody LikeRequestDto dto, @AuthenticationPrincipal User user){

         likeService.postLike(dto, user);
    }
}
