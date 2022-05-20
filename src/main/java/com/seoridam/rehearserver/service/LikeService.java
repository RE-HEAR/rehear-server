package com.seoridam.rehearserver.service;

import com.seoridam.rehearserver.domain.Interview;
import com.seoridam.rehearserver.domain.Like;
import com.seoridam.rehearserver.domain.User;
import com.seoridam.rehearserver.dto.LikeRequestDto;
import com.seoridam.rehearserver.global.common.LikeTypeEnum;
import com.seoridam.rehearserver.repository.InterviewRepository;
import com.seoridam.rehearserver.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final InterviewRepository interviewRepository;

    public void postLike(LikeRequestDto dto, User user){

        if(user == null)
            throw new IllegalArgumentException("사용자 인증 정보가 부족합니다.");

        Optional<Interview> interview = interviewRepository.findById(dto.getInterviewId());
        if(interview.isEmpty())
            throw new IllegalArgumentException("해당 인터뷰 게시글이 없습니다.");

        Like like = Like.builder()
                .interview(interview.get())
                .user(user)
                .likeType(dto.getLikeType())
                .build();

        if(checkIsLikeExist(user.getId(), interview.get().getId())){
            likeRepository.deleteByUserIdAndInterviewId(user.getId(), interview.get().getId());
            if(!checkIsLikeTypeEqual(user.getId(), interview.get().getId(), dto.getLikeType()))  //error
                likeRepository.save(like);
        }
        else
            likeRepository.save(like);
    }

    private boolean checkIsLikeExist(long userId, long interviewId) {
        return likeRepository.findByUserIdAndInterviewId(userId, interviewId).isPresent();
    }

    private boolean checkIsLikeTypeEqual(long userId, long interviewId, LikeTypeEnum likeType) {
        return likeRepository.findByUserIdAndInterviewId(userId, interviewId).get().getLikeType().equals(likeType);
    }
}
