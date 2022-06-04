package com.seoridam.rehearserver.service;

import com.seoridam.rehearserver.domain.Article;
import com.seoridam.rehearserver.domain.Like;
import com.seoridam.rehearserver.domain.User;
import com.seoridam.rehearserver.dto.LikeRequestDto;
import com.seoridam.rehearserver.global.common.LikeTypeEnum;
import com.seoridam.rehearserver.repository.ArticleRepository;
import com.seoridam.rehearserver.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final ArticleRepository articleRepository;

    public void postLike(LikeRequestDto dto, User user){

        if(user == null)
            throw new IllegalArgumentException("사용자 인증 정보가 부족합니다.");

        Optional<Article> article = articleRepository.findById(dto.getArticleId());
        if(article.isEmpty())
            throw new IllegalArgumentException("해당 인터뷰 게시글이 없습니다.");

        Like like = Like.builder()
                .article(article.get())
                .user(user)
                .likeType(dto.getLikeType())
                .build();

        if(checkIsLikeExist(user.getId(), article.get().getId())){
            likeRepository.deleteByUserIdAndArticleId(user.getId(), article.get().getId());
            if(!checkIsLikeTypeEqual(user.getId(), article.get().getId(), dto.getLikeType()))  //error
                likeRepository.save(like);
        }
        else
            likeRepository.save(like);
    }

    private boolean checkIsLikeExist(long userId, long articleId) {
        return likeRepository.findByUserIdAndArticleId(userId, articleId).isPresent();
    }

    private boolean checkIsLikeTypeEqual(long userId, long articleId, LikeTypeEnum likeType) {
        return likeRepository.findByUserIdAndArticleId(userId, articleId).get().getLikeType().equals(likeType);
    }
}
